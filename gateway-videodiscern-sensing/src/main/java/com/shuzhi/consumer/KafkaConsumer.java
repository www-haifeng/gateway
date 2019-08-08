package com.shuzhi.consumer;


import com.shuzhi.cache.Cache;
import com.shuzhi.common.ReportUtils;
import com.shuzhi.entity.CommandInfo;
import com.shuzhi.entity.TGatewayConfigEntity;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private ReportUtils reportUtils;

    @KafkaListener(topics = {"Comparison","facePublish"}, groupId = "group1")
    public void listner(ConsumerRecord<String, byte[]> record) {
        try {
            reportUtils.reportTypeSelect(record);
        } catch (Exception e) {
            logger.error("消息解析异常：" + e.getMessage());
            e.printStackTrace();
        }

    }

}
