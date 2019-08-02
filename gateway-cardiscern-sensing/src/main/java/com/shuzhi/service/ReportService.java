package com.shuzhi.service;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuzhi.cache.Cache;
import com.shuzhi.commen.ConfigData;
import com.shuzhi.commen.HttpCommandUtils;
import com.shuzhi.commen.Utils;
import com.shuzhi.entity.CommandInfo;
import com.shuzhi.entity.SystemInfoData;
import com.shuzhi.entity.report.MessageReportData;
import com.shuzhi.producer.RabbitSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description: 上报 相关 业务
 * @Author: lirb
 * @CreateDate: 2019/7/15 13:31
 * @Version: 1.0
 **/
@Component
@EnableConfigurationProperties(ConfigData.class)
public class ReportService {

    private final static Logger logger = LoggerFactory.getLogger(ReportService.class);

    @Autowired
    ConfigData configData;
    @Autowired
    private RabbitSender rabbitSender;
    @Autowired
    private Utils utils;

    /**
     * 上报结果集发送到 mq
     * @param resultJSON ：结果数据
     * @param systemInfoData ：结果体
     */
    public void reportSend(Object resultJSON, SystemInfoData systemInfoData){
        String timeStamp = utils.getTimeStamp();
        //命令正确执行
        if (resultJSON !=null && !"".equals(resultJSON)){
            MessageReportData messageRevertData = getReportMsgRevertData(resultJSON);
            String mrdJSON = JSON.toJSONString(messageRevertData);
            systemInfoData.setMsgts(timeStamp);
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
            MessageReportData messageRevertData = getReportMsgRevertData(resultJSON);
            String mrdJSON = JSON.toJSONString(messageRevertData);
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

    /**
     * 封装 msg层数据
     * @param resultJson :结果及
     * @return
     */
    private MessageReportData getReportMsgRevertData(Object resultJson) {
        MessageReportData mrd = new MessageReportData();
        Map<String, CommandInfo> commandMap = Cache.commandMap;
        String fristKey = commandMap.keySet().iterator().next();
        CommandInfo commandInfo = commandMap.get(fristKey);
        mrd.setType(commandInfo.getTdeviceFactoryEntity().getType());
        mrd.setSubtype(commandInfo.getTdeviceFactoryEntity().getSubtype());
        mrd.setInfoid(configData.getInfoId());
        mrd.setDid("");
        mrd.setData(resultJson);
        return mrd;
    }
}
