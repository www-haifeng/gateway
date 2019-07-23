package com.shuzhi.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shuzhi.common.ConfigData;
import com.shuzhi.common.Utils;
import com.shuzhi.dao.FactoryCronDao;
import com.shuzhi.entity.CommandInfo;
import com.shuzhi.entity.MessageRevertData;
import com.shuzhi.entity.SystemInfoData;
import com.shuzhi.producer.RabbitSender;
import com.shuzhi.schedule.DynamicScheduleTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述 命令业务逻辑处理
 *
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
    private DynamicScheduleTask dynamicScheduleTask;

    @Autowired
    private FactoryCronDao factoryCronDao;
    /**
     * @Description: 统一处理请求
     * @Author: YHF
     * @date 2019/6/10
     */
    public void commandService(String dataJson, CommandInfo commandInfo, SystemInfoData systemInfoData) {
        try {
            //动态更改任务调度时间
            JSONObject jsonObject = JSON.parseObject(dataJson);
            Integer intervaltime = (Integer) jsonObject.get("intervaltime");
            String cron = utils.parseCron(intervaltime);
            logger.info("cron表达式："+cron);
            dynamicScheduleTask.setCron(cron);
            //修改数据库时间
            factoryCronDao.updateCronByFactoryId(commandInfo.getTdeviceFactoryEntity().getId(),cron);
            logger.info("修改cron表达式成功");
            //发送响应
            Map<String, String> reultData = new HashMap<>();
            reultData.put("type", "sucess");
            commandSend(JSON.toJSONString(reultData),systemInfoData);
        } catch (Exception e) {
            e.printStackTrace();
            //发送响应
            Map<String, String> reultData = new HashMap<>();
            reultData.put("type", "failed");
            commandSend(JSON.toJSONString(reultData),systemInfoData);
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
        if (resultJSON !=null && !"".equals(resultJSON) && !"failed".equals(resultJSON)){
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
