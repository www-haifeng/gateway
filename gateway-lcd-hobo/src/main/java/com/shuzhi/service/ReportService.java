package com.shuzhi.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shuzhi.cache.Cache;
import com.shuzhi.common.ConfigData;
import com.shuzhi.common.HttpCommandUtils;
import com.shuzhi.common.Utils;
import com.shuzhi.entity.CommandInfo;
import com.shuzhi.entity.SystemInfoData;
import com.shuzhi.entity.ReportMsgRevertData;
import com.shuzhi.producer.RabbitSender;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

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


    /**
     * 上报业务处理
     *
     * @param reportUrl   ：请求url
     * @param requestBody ：请求体
     */
    public void reportService(String reportUrl, SystemInfoData requestBody) {
        //调用请求
        String resultJSON = httpCommandUtils.getHttp(reportUrl);
        logger.info("请求返回结果:" + resultJSON);
        reportSend(reportUrl, resultJSON, requestBody);
    }

    /**
     * 上报结果集发送到 mq
     *
     * @param reportUrl   :请求url
     * @param resultJson  ：结果集
     * @param requestBody ：请求body
     */
    public void reportSend(String reportUrl, String resultJson, SystemInfoData requestBody) {
        String timeStamp = utils.getTimeStamp();
        //命令正确执行
        if (resultJson != null && !"".equals(resultJson)) {
            ReportMsgRevertData messageRevertData = getReportMsgRevertData(reportUrl, resultJson);
            String mrdJSON = messageRevertData.toString();
            requestBody.setMsgts(timeStamp);
            requestBody.setMsg(mrdJSON);

            requestBody.setSign(utils.getSignVerify(requestBody));
            String commandRevertJSON = requestBody.toString();
            try {
                rabbitSender.send("upMessage","upMessage", commandRevertJSON);
                logger.info("命令回执发送完毕:" + commandRevertJSON);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //命令执行未成功
            ReportMsgRevertData messageRevertData = getReportMsgRevertData(reportUrl, resultJson);
            String mrdJSON = messageRevertData.toString();
            requestBody.setMsgts(timeStamp);
            requestBody.setMsg(mrdJSON);
            requestBody.setSign(utils.getSignVerify(requestBody));
            String commandRevertJSON = requestBody.toString();
            try {
                rabbitSender.send("upMessage","upMessage", commandRevertJSON);
                logger.error("命令执行失败，请查看原因:" + commandRevertJSON);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    private ReportMsgRevertData getReportMsgRevertData(String requestUrl, String data) {
        ReportMsgRevertData mrd = new ReportMsgRevertData();
        Map<String, CommandInfo> commandMap = Cache.commandMap;
        String fristKey = commandMap.keySet().iterator().next();
        CommandInfo commandInfo = commandMap.get(fristKey);
        mrd.setType(commandInfo.getTdeviceFactoryEntity().getType());
        mrd.setSubtype(commandInfo.getTdeviceFactoryEntity().getSubtype());
        mrd.setInfoid("123456");
        mrd.setDid("\"\"");
        try {
            JSONArray objects = JSONArray.parseArray(data);
            JSONObject jsonObject = objects.getJSONObject(0);
            //如果是查询终端相关接口，并且结果集有数据 ，将 设备id转换成 平台id
            if (requestUrl.contains("/vs/api/apiselclient.vs") ||
                    requestUrl.contains("/vs/api/apiselzxclient.vs") ||
                    requestUrl.contains("/vs/api/apisellxclient.vs")) {

                String clientlistStr = JSON.toJSONString(jsonObject.get("clientlist"));
                List<Map<String, Object>> clientList = (List<Map<String, Object>>) JSONArray.parse(clientlistStr);
                if (CollectionUtils.isNotEmpty(clientList)) {
                    for (Map<String, Object> datamap : clientList) {
                        if (datamap.containsKey("id")) {
                            String did = Utils.deviceIdToDid(datamap.get("id").toString());
                            datamap.put("id", did);
                        }
                    }
                    jsonObject.put("clientlist", clientList);
                    mrd.setData(jsonObject.toJSONString());
                }
            } else {
                mrd.setData(jsonObject.toJSONString());
            }

        } catch (Exception e) {
            logger.error("命令执行返回结果集解析失败");
        }
        return mrd;
    }
}
