package com.shuzhi.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuzhi.cache.Cache;
import com.shuzhi.common.ConfigData;
import com.shuzhi.common.HttpCommandUtils;
import com.shuzhi.common.Utils;
import com.shuzhi.entity.*;
import com.shuzhi.producer.RabbitSender;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
public class        ReportService {

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
            if (resultJSON != null && !"".equals(resultJSON)) {
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
            }
        } catch (Exception e) {
           logger.error("上报请求处理结果集中boolean数据失败：{}",e);
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
            ReportMsgRevertData messageRevertData = getReportMsgRevertData(Cache.reportResultList);
            String mrdJSON = JSON.toJSONString(messageRevertData);
            systemInfoData.setMsgts(timeStamp);
            systemInfoData.setMsg(mrdJSON);

            systemInfoData.setSign(utils.getSignVerify(systemInfoData));
            String commandRevertJSON = systemInfoData.toString();
            try {
                rabbitSender.send("upMessage","upMessage", commandRevertJSON);
                logger.info("schedule---定时请求设备状态发送完毕:" + commandRevertJSON);
                //清空缓存
                Cache.reportResultList = new ArrayList<>();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //命令执行未成功
            ReportMsgRevertData messageRevertData = getReportMsgRevertData(Cache.reportResultList);
            String mrdJSON = JSON.toJSONString(messageRevertData);
            systemInfoData.setMsgts(timeStamp);
            systemInfoData.setMsg(mrdJSON);
            systemInfoData.setSign(utils.getSignVerify(systemInfoData));
            String commandRevertJSON = systemInfoData.toString();
            try {
                rabbitSender.send("upMessage","upMessage", commandRevertJSON);
                logger.error("schedule---定时请求，请查看原因:" + commandRevertJSON);
                //清空缓存
                Cache.reportResultList = new ArrayList<>();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 封装 msg层数据
     * @param reportResultList :结果list
     * @return
     */
    private ReportMsgRevertData getReportMsgRevertData( List<ReportResult> reportResultList) {
        ReportMsgRevertData mrd = new ReportMsgRevertData();
        Map<String, CommandInfo> commandMap = Cache.commandMap;
        String fristKey = commandMap.keySet().iterator().next();
        CommandInfo commandInfo = commandMap.get(fristKey);
        mrd.setType(commandInfo.getTdeviceFactoryEntity().getType());
        mrd.setSubtype(commandInfo.getTdeviceFactoryEntity().getSubtype());
        mrd.setInfoid(configData.getInfoId());
        mrd.setDid("\"\"");
        Map<String, List<ReportResult>> dataMap = new HashMap<>();
        dataMap.put("clientlist",reportResultList);
        mrd.setData(dataMap);
        return mrd;
    }

}
