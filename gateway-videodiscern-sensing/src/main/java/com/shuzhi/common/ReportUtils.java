package com.shuzhi.common;

import com.shuzhi.cache.Cache;
import com.shuzhi.entity.AlertMsgBody;
import com.shuzhi.entity.AlertMsgBody.pbAlertInfoEx;
import com.shuzhi.entity.MQMsg;
import com.shuzhi.entity.MQMsg.pbCapFaceInfoEx;
import com.shuzhi.entity.SystemInfoData;
import com.shuzhi.entity.report.AlertMsgData;
import com.shuzhi.entity.report.CapFaceInfoData;
import com.shuzhi.service.ReportService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Description: 上报相关业务工具类
 * @Author: lirb
 * @CreateDate: 2019/7/15 13:35
 * @Version: 1.0
 **/
@Component
public class ReportUtils {

    private final static Logger logger = LoggerFactory.getLogger(ReportUtils.class);

    @Autowired
    private ReportService reportService;
    @Autowired
    private ConfigData configData;


    /**
     * 上报类型解析
     *
     * @param record
     */
    public void reportTypeSelect(ConsumerRecord<String, byte[]> record)throws Exception{
        SystemInfoData systemInfoData = getRequestBody();
            String topic = record.topic();
        switch (topic){
            //抓拍人脸
            case "Comparison":
                pbCapFaceInfoEx capFaceInfoEx = MQMsg.pbCapFaceInfoEx.parseFrom(record.value());
                CapFaceInfoData capFaceInfoData = new CapFaceInfoData(capFaceInfoEx);
                reportService.reportSend(capFaceInfoData, systemInfoData);
                break;
            //告警
            case "facePublish":
                pbAlertInfoEx alertInfoEx = AlertMsgBody.pbAlertInfoEx.parseFrom(record.value());
                AlertMsgData alertMsgData = new AlertMsgData(alertInfoEx);
                reportService.reportSend(alertMsgData, systemInfoData);
                break;

                default:
                    logger.info("消息主体错误: topic = {}", topic);
        }
    }

    /**
     * 上报请求实体
     *
     * @return
     */
    public SystemInfoData getRequestBody() {
        //暂时写死，后期从数据库取
        SystemInfoData infoData = new SystemInfoData();
        infoData.setMsgid(UUID.randomUUID().toString());
        infoData.setMsgtype(4);
        infoData.setSystype(configData.getSysType());
        infoData.setSysid(Integer.parseInt(Cache.gatewayConfigEntity.getSysId()));
        infoData.setConnectid(Integer.parseInt(Cache.gatewayConfigEntity.getConnectId()));
        return infoData;
    }


}
