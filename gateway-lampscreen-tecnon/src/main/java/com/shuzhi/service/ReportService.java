package com.shuzhi.service;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuzhi.cache.Cache;
import com.shuzhi.common.ConfigData;
import com.shuzhi.common.HttpCommandUtils;
import com.shuzhi.common.Utils;
import com.shuzhi.entity.DeviceInfo;
import com.shuzhi.entity.MessageRevertData;
import com.shuzhi.entity.ReportResult;
import com.shuzhi.entity.SystemInfoData;
import com.shuzhi.producer.RabbitSender;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

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
    @Autowired
    private HttpCommandUtils httpCommandUtils;
    @Autowired
    private ObjectMapper mapper;

    /**
     * 上报业务处理
     *
     * @param url         ：请求url
     * @param commandJSON ：请求参数
     * @param deviceInfo  ：设备实体
     */
    public void reportService(String url, String commandJSON, DeviceInfo deviceInfo) {
        try {
            //调用请求
            String resultJSON = httpCommandUtils.postHTTP(url, commandJSON);
            logger.info("请求返回结果:" + resultJSON);
            //结果集中包含Boolean 处理成统一协议格式
            JsonNode jsonNode = mapper.readTree(resultJSON).get("result");
            //取出返回结果
            Boolean resultBoolean = jsonNode.traverse(mapper).readValueAs(Boolean.class);
            //Boolean处理成魔法数
            ReportResult rr = new ReportResult();
            rr.setId(deviceInfo.getTdeviceTecnonEntity().getDid());
            if (resultBoolean) {
                rr.setResult(1);
            } else if (!resultBoolean) {
                rr.setResult(0);
            }

            //放入缓存
            Cache.reportResultList.add(rr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 上报结果集发送到 mq
     *
     * @param systemInfoData ：结果体
     */
    public void reportSend(SystemInfoData systemInfoData) {
        String timeStamp = utils.getTimeStamp();
        //命令正确执行
        if (CollectionUtils.isNotEmpty(Cache.reportResultList)) {
            MessageRevertData messageRevertData = utils.getMessageRevertData(configData.getSeccussCode(), timeStamp, JSON.toJSONString(Cache.reportResultList));
            String mrdJSON = messageRevertData.toString();
            systemInfoData.setMsgts(timeStamp);
            systemInfoData.setMsg(mrdJSON);

            systemInfoData.setSign(utils.getSignVerify(systemInfoData));
            String commandRevertJSON = systemInfoData.toString();
            try {
                rabbitSender.send("lowerControlMessage", commandRevertJSON);
                logger.info("命令回执发送完毕:" + commandRevertJSON);
                //清空缓存
                Cache.reportResultList = new ArrayList<>();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //命令执行未成功
            MessageRevertData messageRevertData = utils.getMessageRevertData(configData.getFailedCode(), timeStamp, JSON.toJSONString(Cache.reportResultList));
            String mrdJSON = messageRevertData.toString();
            systemInfoData.setMsgts(timeStamp);
            systemInfoData.setMsg(mrdJSON);
            systemInfoData.setSign(utils.getSignVerify(systemInfoData));
            String commandRevertJSON = systemInfoData.toString();
            try {
                rabbitSender.send("lowerControlMessage", commandRevertJSON);
                logger.error("命令执行失败，请查看原因:" + commandRevertJSON);
                //清空缓存
                Cache.reportResultList = new ArrayList<>();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}
