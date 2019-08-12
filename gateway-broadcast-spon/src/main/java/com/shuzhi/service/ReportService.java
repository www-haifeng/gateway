package com.shuzhi.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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

import java.util.HashMap;
import java.util.List;
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
    @Autowired
    private HttpCommandUtils httpCommandUtils;
    @Autowired
    private ObjectMapper mapper;

    /**
     * 上报业务处理
     *
     * @param url            ：请求url
     * @param commandJSON    ：请求参数
     * @param systemInfoData ：请求返回体
     */
    public void reportService(String url, String commandJSON, SystemInfoData systemInfoData) {
        try {
            //调用请求
            String resultJSON = httpCommandUtils.postHTTP(url, commandJSON);
            logger.info("请求返回结果:" + resultJSON);

            //解析返回结果 转换成对象
            Receipt receiptData = mapper.readValue(resultJSON, Receipt.class);

            //处理device与did映射关系
            List<Map<String, Object>> clientList = (List<Map<String, Object>>) JSONArray.parse(receiptData.getRows().toString());
            if (CollectionUtils.isNotEmpty(clientList)) {
                for (Map<String, Object> datamap : clientList) {
                    if (datamap.containsKey("id")) {
                        if (!StringUtil.isEmpty(Cache.device_IdMap.get(datamap.get("id").toString()))) {
                            datamap.put("id", Cache.device_IdMap.get(datamap.get("id").toString()));
                        }
                    }
                }
            }
            String json = mapper.writeValueAsString(clientList);
            System.out.println(json);

            receiptData.setRows(JSONArray.parseArray(json));
            logger.info("处理后的结果:" + receiptData.toString());
            reportSend(receiptData.toString(), systemInfoData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 上报结果集发送到 mq
     *
     * @param resultJSON     ：结果数据
     * @param systemInfoData ：结果体
     */
    public void reportSend(String resultJSON, SystemInfoData systemInfoData) {
        String timeStamp = utils.getTimeStamp();
        //命令正确执行
        if (resultJSON != null && !"".equals(resultJSON)) {
            ReportMsgRevertData messageRevertData = getReportMsgRevertData(resultJSON);
            String mrdJSON = messageRevertData.toString();
            systemInfoData.setMsgts(timeStamp);
            systemInfoData.setMsg(mrdJSON);

            systemInfoData.setSign(utils.getSignVerify(systemInfoData));
            String commandRevertJSON = systemInfoData.toString();
            try {
                rabbitSender.send("upMessage","upMessage", commandRevertJSON);
                logger.info("命令回执发送完毕:" + commandRevertJSON);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //命令执行未成功
            ReportMsgRevertData messageRevertData = getReportMsgRevertData(resultJSON);
            String mrdJSON = messageRevertData.toString();
            systemInfoData.setMsgts(timeStamp);
            systemInfoData.setMsg(mrdJSON);
            systemInfoData.setSign(utils.getSignVerify(systemInfoData));
            String commandRevertJSON = systemInfoData.toString();
            try {
                rabbitSender.send("upMessage","upMessage", commandRevertJSON);
                logger.error("命令执行失败，请查看原因:" + commandRevertJSON);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 封装 msg层数据
     *
     * @param resultJson :结果及
     * @return
     */
    private ReportMsgRevertData getReportMsgRevertData(String resultJson) {
        ReportMsgRevertData mrd = new ReportMsgRevertData();
        Map<String, CommandInfo> commandMap = Cache.commandMap;
        String fristKey = commandMap.keySet().iterator().next();
        CommandInfo commandInfo = commandMap.get(fristKey);
        mrd.setType(commandInfo.getTdeviceFactoryEntity().getType());
        mrd.setSubtype(commandInfo.getTdeviceFactoryEntity().getSubtype());
        mrd.setInfoid(configData.getInfoId());
        mrd.setDid("\"\"");
        mrd.setData(resultJson);
        return mrd;
    }
}
