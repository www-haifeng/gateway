package com.shuzhi.conusmer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuzhi.entity.KafkaCapMsgM;
import com.shuzhi.entity.KafkaCapMsgM.pbcapturemsg;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
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
    private ObjectMapper mapper;

    @KafkaListener(topics = "ViStrCap", groupId = "testGroup")
    public void listner(ConsumerRecords<String, byte[]> records) {
        try {
            for (ConsumerRecord<String,byte[]> record : records) {
                pbcapturemsg pbcm = KafkaCapMsgM.pbcapturemsg.parseFrom(record.value());
                System.out.println(pbcm.getUuid());
                System.out.println(pbcm.getDeviceId());
                System.out.println(pbcm.getAge());
                System.out.println(pbcm.getCapUrl());
                System.out.println(pbcm.getCapLocation());
                System.out.println();
            }
//              System.out.println(record);
//            System.out.println(record.value());
//              pbcapturemsg pbcm = KafkaCapMsgM.pbcapturemsg.parseFrom(record.value());
////            HashMap valueMap = mapper.readValue(record.value(), HashMap.class);
////            JSONObject jsonObject = JSON.parseObject(record.value());
//            logger.info("消费kafka消息:" + JSON.toJSONString(pbcm));

        }catch (Exception e){
            logger.error("消息解析异常："+e.getMessage());
            e.printStackTrace();
        }

    }

}
