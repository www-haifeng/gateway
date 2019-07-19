package com.shuzhi.service;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuzhi.common.ConfigData;
import com.shuzhi.common.HttpCommandUtils;
import com.shuzhi.common.Utils;
import com.shuzhi.entity.SystemInfoData;
import com.shuzhi.entity.MessageRevertData;
import com.shuzhi.entity.commandResult.*;
import com.shuzhi.producer.RabbitSender;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 功能描述 命令业务逻辑处理
 * @author YHF
 * @date 2019/6/6
 * @params
 * @return
 */
@Component
@EnableConfigurationProperties(ConfigData.class)
public class CommandService {
    private final static Logger logger = LoggerFactory.getLogger(CommandService.class);
    @Autowired
    ConfigData configData;
    @Autowired
    private RabbitSender rabbitSender;
    @Autowired
    private Utils utils;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private HttpCommandUtils httpCommandUtils;

    /**
     * @Description: 统一处理请求 返回Boolean类型
     * @Author: YHF
     * @date 2019/6/10
     */
    public void commandServiceBoolean(String url, String commandJSON, SystemInfoData systemInfoData){
        try {
            //调用请求
            String resultJSON = httpCommandUtils.postHTTP(url, commandJSON);
            //结果集中包含Boolean 处理成统一协议格式
            JsonNode jsonNode = mapper.readTree(resultJSON).get("result");
            //取出返回结果
            Boolean resultBoolean = jsonNode.traverse(mapper).readValueAs(Boolean.class);
            //Boolean处理成魔法数
            CommonResult cr = new CommonResult();
            if (resultBoolean){
                cr.setResult(1);
            }else if (!resultBoolean){
                cr.setResult(0);
            }
            logger.info("请求返回结果:"+cr.toString());
            commandSend(cr.toString(),systemInfoData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 统一处理请求 返回Integer类型
     * @Author: YHF
     * @date 2019/6/10
     */
    public void commandServiceInteger(String url, String commandJSON, SystemInfoData systemInfoData){
        try {
            //调用请求
            String resultJSON = httpCommandUtils.postHTTP(url, commandJSON);
            //结果集中包含Boolean 处理成统一协议格式
            JsonNode jsonNode = mapper.readTree(resultJSON).get("result");
            //取出返回结果
            Integer resultInteger = jsonNode.traverse(mapper).readValueAs(Integer.class);
            //包装统一格式
            CommonResult cr = new CommonResult();
            cr.setResult(resultInteger);
            logger.info("请求返回结果:"+cr.toString());
            commandSend(cr.toString(),systemInfoData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 统一处理请求 返回String类型
     * @Author: YHF
     * @date 2019/6/10
     */
    public void commandServiceString(String url, String commandJSON, SystemInfoData systemInfoData){
        try {
            //调用请求
            String resultJSON = httpCommandUtils.postHTTP(url, commandJSON);
            //结果集中包含Boolean 处理成统一协议格式
            JsonNode jsonNode = mapper.readTree(resultJSON).get("_type");
            //取出返回结果
            String resultStr = jsonNode.traverse(mapper).readValueAs(String.class);
            //包装统一格式
            XwalkLoadPageResult xlpr = new XwalkLoadPageResult();
            xlpr.setType(resultStr);
            logger.info("请求返回结果:"+xlpr.toString());
            commandSend(xlpr.toString(),systemInfoData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @Description: 统一处理请求 返回Class类型
     * @Author: YHF
     * @date 2019/6/10
     */

    public void commandServiceClass(String url, String commandJSON, SystemInfoData systemInfoData,String commandType){
        try {
            //调用请求
            String resultJSON = httpCommandUtils.postHTTP(url, commandJSON);
            if ("queryLoaclFileSize".equals(commandType)){
                QueryLoaclFileSizeResult qlfsr = mapper.readValue(resultJSON, QueryLoaclFileSizeResult.class);
                logger.info("请求返回结果:"+qlfsr.toString());
                commandSend(qlfsr.toString(),systemInfoData);
            }else if ("getScreenshots".equals(commandType)){
                GetScreenShotsResult gssr = mapper.readValue(resultJSON.replace("\\n",""), GetScreenShotsResult.class);
                logger.info("请求返回结果:"+gssr.toString());
                commandSend(gssr.toString(),systemInfoData);
            }else if("queryAutomaticBrightness".equals(commandType)){
                QueryAutomaticBrightnessResult qabr = mapper.readValue(resultJSON, QueryAutomaticBrightnessResult.class);
                logger.info("请求返回结果:"+qabr.toString());
                commandSend(qabr.toString(),systemInfoData);
            }else if ("queryTimingBrightness".equals(commandType)){
                //实例化返回对象
                QueryTimingBrightnessResult qtbr = new QueryTimingBrightnessResult();
                //取出接口要使用的参数
                String type = mapper.readTree(resultJSON).get("_type").traverse(mapper).readValueAs(String.class);
                qtbr.setType(type);
                String task = mapper.readTree(resultJSON).get("task").traverse(mapper).readValueAs(String.class);
                if (StringUtils.isEmpty(task) || "null".equals(task)){
                    TaskResult tr = mapper.readValue(resultJSON.replace("\\n",""), TaskResult.class);
                    logger.info("请求返回结果:"+tr.toString());
                    commandSend(tr.toString(),systemInfoData);
                    return;
                }
                //取出默认亮度
                Integer defaultBrightness = mapper.readTree(task).get("defaultBrightness").traverse(mapper).readValueAs(Integer.class);
                qtbr.setDefaultbrightness(defaultBrightness);
                //取出当前亮度
                Integer nowBrightness = mapper.readTree(task).get("brightness").traverse(mapper).readValueAs(Integer.class);
                qtbr.setNowbrightness(nowBrightness);
                //取出结果集中items
                JSONArray items = mapper.readTree(task).get("items").traverse(mapper).readValueAs(JSONArray.class);
                //取出items中schedules
                JSONArray schedules = mapper.readTree(items.getJSONObject(0).toString()).get("schedules").traverse(mapper).readValueAs(JSONArray.class);
                //取出控制时间范围内的亮度
                Integer brightness = mapper.readTree(items.getJSONObject(0).toString()).get("brightness").traverse(mapper).readValueAs(Integer.class);

                qtbr.setBrightness(brightness);
                //取出schedules中需要的元素
                String startDate = mapper.readTree(schedules.getJSONObject(0).toString()).get("startDate").traverse(mapper).readValueAs(String.class);
                qtbr.setStartdate(startDate);
                String endDate = mapper.readTree(schedules.getJSONObject(0).toString()).get("endDate").traverse(mapper).readValueAs(String.class);
                qtbr.setEnddate(endDate);
                String startTime = mapper.readTree(schedules.getJSONObject(0).toString()).get("startTime").traverse(mapper).readValueAs(String.class);
                qtbr.setStarttime(startTime);
                String endTime = mapper.readTree(schedules.getJSONObject(0).toString()).get("endTime").traverse(mapper).readValueAs(String.class);
                qtbr.setEndtime(endTime);
                logger.info("请求返回结果:"+qtbr.toString());
                commandSend(qtbr.toString(),systemInfoData);
            }else if ("queryTimingSwitchScreen".equals(commandType)){
                //实例化返回对象
                QueryTimingSwitchScreen qtss = new QueryTimingSwitchScreen();
                //取出接口要使用的参数
                String type = mapper.readTree(resultJSON).get("_type").traverse(mapper).readValueAs(String.class);
                qtss.setType(type);
                String task = mapper.readTree(resultJSON).get("task").traverse(mapper).readValueAs(String.class);
                if (StringUtils.isEmpty(task) || "null".equals(task)){
                    TaskResult tr = mapper.readValue(resultJSON.replace("\\n",""), TaskResult.class);
                    logger.info("请求返回结果:"+tr.toString());
                    commandSend(tr.toString(),systemInfoData);
                    return;
                }
                //取出结果集中items
                JSONArray schedules = mapper.readTree(task).get("schedules").traverse(mapper).readValueAs(JSONArray.class);

                //取出schedules中需要的元素
                String startDate = mapper.readTree(schedules.getJSONObject(0).toString()).get("startDate").traverse(mapper).readValueAs(String.class);
                qtss.setStartdate(startDate);
                String endDate = mapper.readTree(schedules.getJSONObject(0).toString()).get("endDate").traverse(mapper).readValueAs(String.class);
                qtss.setEnddate(endDate);
                String startTime = mapper.readTree(schedules.getJSONObject(0).toString()).get("startTime").traverse(mapper).readValueAs(String.class);
                qtss.setStarttime(startTime);
                String endTime = mapper.readTree(schedules.getJSONObject(0).toString()).get("endTime").traverse(mapper).readValueAs(String.class);
                qtss.setEndtime(endTime);
                logger.info("请求返回结果:"+qtss.toString());
                commandSend(qtss.toString(),systemInfoData);
            }else if("queryTimingRestartDevice".equals(commandType)) {
                TimingRestartDeviceResult trdr = mapper.readValue(resultJSON, TimingRestartDeviceResult.class);
                logger.info("请求返回结果:" + trdr.toString());
                commandSend(trdr.toString(), systemInfoData);
            }
            } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description:组装命令返回rabbit
     * @Author: YHF
     * @date 2019/6/6
     */
    private void commandSend(String resultJSON, SystemInfoData systemInfoData){
            String timeStamp = utils.getTimeStamp();
            //命令正确执行
            if (resultJSON !=null && !"".equals(resultJSON)){
                MessageRevertData messageRevertData = utils.getMessageRevertData(configData.getSeccussCode(), timeStamp, resultJSON);
                String mrdJSON = messageRevertData.toString();
                systemInfoData.setMsgts(timeStamp);
                systemInfoData.setMsgtype(configData.getMsgtypeCommandReturn());
                systemInfoData.setMsg(mrdJSON);

                systemInfoData.setSign(utils.getSignVerify(systemInfoData));
                String commandRevertJSON = systemInfoData.toString();
                try {
                    rabbitSender.send("lowerControlMessage","lowerControlMessage",commandRevertJSON);
                    logger.info("命令回执发送完毕:"+commandRevertJSON);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                //命令执行未成功
                MessageRevertData messageRevertData = utils.getMessageRevertData(configData.getFailedCode(), timeStamp, resultJSON);
                String mrdJSON = messageRevertData.toString();
                systemInfoData.setMsgts(timeStamp);
                systemInfoData.setMsg(mrdJSON);
                systemInfoData.setSign(utils.getSignVerify(systemInfoData));
                String commandRevertJSON = systemInfoData.toString();
                try {
                    rabbitSender.send("lowerControlMessage","lowerControlMessage",commandRevertJSON);
                    logger.error("命令执行失败，请查看原因:"+commandRevertJSON);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

    }

}
