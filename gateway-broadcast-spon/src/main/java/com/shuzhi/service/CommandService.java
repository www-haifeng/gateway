package com.shuzhi.service;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuzhi.cache.Cache;
import com.shuzhi.commen.ConfigData;
import com.shuzhi.commen.HttpCommandUtils;
import com.shuzhi.commen.Utils;
import com.shuzhi.entity.*;
import com.shuzhi.producer.RabbitSender;
import com.shuzhi.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
    private HttpCommandUtils httpCommandUtils;
    @Autowired
    private ObjectMapper mapper;
    /**
     * @Description: 统一处理请求
     * @Author: YHF
     * @date 2019/6/10
     */
    public void commandService(String url,String commandJSON,SystemInfoData systemInfoData){
        try {
            //调用请求
            String resultJSON = httpCommandUtils.postHTTP(url, commandJSON);
            logger.info("请求返回结果:"+resultJSON);
            commandSend(resultJSON,systemInfoData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @Description: 处理deviceId为did
     * @Author: YHF
     * @date 2019/7/12
     */
    public void commandServiceGetzoneterminaldata(String url,String commandJSON,SystemInfoData systemInfoData){
        try {
            //调用请求
            String resultJSON = httpCommandUtils.postHTTP(url, commandJSON);
            logger.info("请求返回结果:"+resultJSON);

            //解析返回结果 转换成对象
            Receipt receiptData = mapper.readValue(resultJSON, Receipt.class);

            //处理device与did映射关系
            List<Map<String, Object>> clientList = (List<Map<String, Object>>) JSONArray.parse(receiptData.getRows().toString());
            if (CollectionUtils.isNotEmpty(clientList)) {
                for (Map<String, Object> datamap : clientList) {
                    if (datamap.containsKey("id")) {
                        if (!StringUtil.isEmpty(Cache.device_IdMap.get(datamap.get("id").toString()))){
                            datamap.put("id", Cache.device_IdMap.get(datamap.get("id").toString()));
                        }
                    }
                }
            }
            String json = mapper.writeValueAsString(clientList);
            System.out.println(json);


            receiptData.setRows(JSONArray.parseArray(json));
            logger.info("处理后的结果:"+receiptData.toString());
            commandSend(receiptData.toString(),systemInfoData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 处理查询计划任务单个中任务的 did转换问题
     * @Author: YHF
     * @date 2019/6/10
     */
    public void commandServiceGettaskinfo(String url,String commandJSON,SystemInfoData systemInfoData){
        try {
            //调用请求
            String resultJSON = httpCommandUtils.postHTTP(url, commandJSON);

            if (!StringUtil.isEmpty(resultJSON)){
            //解析返回结果 转换成对象
            ReceiptJob receiptJob = mapper.readValue(resultJSON, ReceiptJob.class);

            //处理device与did映射关系
            List<Map<String, Object>> triggersList = (List<Map<String, Object>>) JSONArray.parse(receiptJob.getTriggers().toString());
            if (CollectionUtils.isNotEmpty(triggersList)) {
                for (Map<String, Object> datamap : triggersList) {
                    if (datamap.containsKey("eventid")) {
                        //处理参数中的device转成did
                        if (!StringUtil.isEmpty(utils.deviceIdToDid(datamap.get("eventid").toString(),"<"))){
                            datamap.put("eventid", utils.deviceIdToDid(datamap.get("eventid").toString(),"<"));
                        }
                    }
                }
            }
            String triggersJson = mapper.writeValueAsString(triggersList);
            System.out.println(triggersJson);
            receiptJob.setTriggers(JSONArray.parseArray(triggersJson));

            //处理device与did映射关系
            List<Map<String, Object>> commandsList = (List<Map<String, Object>>) JSONArray.parse(receiptJob.getCommands().toString());
            if (CollectionUtils.isNotEmpty(commandsList)) {
                for (Map<String, Object> datamap : commandsList) {
                    if (datamap.containsKey("talksourceid")) {
                        //处理参数中的device转成did
                        if (!StringUtil.isEmpty(Cache.device_IdMap.get(datamap.get("talksourceid").toString()))){
                            datamap.put("talksourceid", Cache.device_IdMap.get(datamap.get("talksourceid").toString()));
                        }
                    }
                    if (datamap.containsKey("talksourcepanel")) {
                        //处理参数中的device转成did
                        if (!StringUtil.isEmpty(Cache.device_IdMap.get(datamap.get("talksourcepanel").toString()))){
                            datamap.put("talksourcepanel", Cache.device_IdMap.get(datamap.get("talksourcepanel").toString()));
                        }
                    }
                    if (datamap.containsKey("monitorsourceid")) {
                        //处理参数中的device转成did
                        if (!StringUtil.isEmpty(Cache.device_IdMap.get(datamap.get("talksourcepanel").toString()))){
                            datamap.put("talksourcepanel", Cache.device_IdMap.get(datamap.get("talksourcepanel").toString()));
                        }
                    }
                    if (datamap.containsKey("monitortargetid")) {
                        //处理参数中的device转成did
                        if (!StringUtil.isEmpty(Cache.device_IdMap.get(datamap.get("monitortargetid").toString()))){
                            datamap.put("monitortargetid", Cache.device_IdMap.get(datamap.get("monitortargetid").toString()));
                        }
                    }
                    if (datamap.containsKey("pasourceid")) {
                        //处理参数中的device转成did
                        if (!StringUtil.isEmpty(Cache.device_IdMap.get(datamap.get("pasourceid").toString()))){
                            datamap.put("pasourceid", Cache.device_IdMap.get(datamap.get("pasourceid").toString()));
                        }
                    }
                    if (datamap.containsKey("targets")) {
                        //处理参数中的device转成did
                        if (!StringUtil.isEmpty(utils.deviceIdToDid(datamap.get("targets").toString(),"<"))){
                            datamap.put("targets", utils.deviceIdToDid(datamap.get("targets").toString(),"<"));
                        }
                    }
                    }
                }
            String commandsJson = mapper.writeValueAsString(commandsList);
            System.out.println(commandsJson);
            receiptJob.setCommands(JSONArray.parseArray(commandsJson));

            logger.info("处理后的结果:"+receiptJob.toString());
            commandSend(receiptJob.toString(),systemInfoData);

            }else{
                commandSend("",systemInfoData);
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
    public void commandSend(String resultJSON, SystemInfoData systemInfoData){
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
                    rabbitSender.send("lowerControlMessage",commandRevertJSON);
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
                    rabbitSender.send("lowerControlMessage",commandRevertJSON);
                    logger.error("命令执行失败，请查看原因:"+commandRevertJSON);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

    }

}
