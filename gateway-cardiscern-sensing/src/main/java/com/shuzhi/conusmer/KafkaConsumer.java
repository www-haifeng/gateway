package com.shuzhi.conusmer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuzhi.commen.ReportUtils;
import com.shuzhi.entity.KafkaCapMsgM;
import com.shuzhi.entity.KafkaCapMsgM.pbcapturemsg;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private ReportUtils reportUtils;

    @KafkaListener(topics = "ViStrCap", groupId = "testGroup")
    public void listner(ConsumerRecord<String, byte[]> record) {
        try {
            pbcapturemsg pbcm = KafkaCapMsgM.pbcapturemsg.parseFrom(record.value());
            reportUtils.reportTypeSelect(pbcm);
        }catch (Exception e){
            logger.error("消息解析异常："+e.getMessage());
            e.printStackTrace();
        }

    }

}
