package com.shuzhi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuzhi.util.JsonConvertBeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by ztt on 2019/6/17
 **/
@RestController
public class RabbitController {
    private static final Logger log= LoggerFactory.getLogger(RabbitController.class);
    private static final String Prefix="rabbit";

//    @Autowired
//    private Environment env;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/send")
    public void sendOrder(@RequestBody Map<String,Object> map){
//    public void sendOrder(String json,
//                          String exchange,
//                          String routingkey) {

//            String orderJson = objectMapper.writeValueAsString(order);
//            Message message = MessageBuilder
//                    .withBody(orderJson.getBytes())
//                    .setContentType(MessageProperties.CONTENT_TYPE_JSON)
//                    .build();

//            String a = "{" +
//                    "\"msgid\": \"b7e84115-3aae-4d72-b49a-ec91a8847482\"," +
//                    "\"msgtype\": 1," +
//                    "\"systype\": 1004," +
//                    "\"sysid\": 1," +
//                    "\"connectid\": 1," +
//                    "\"sign\": \"4634e0d2f0b2b423936eb7651eacc54b98cb248f\"," +
//                    "\"msgts\": \"2015-03-05 17:59:00.567\"," +
//                    "\"msg\": {" +
//                    "\"overtime\": 5," +
//                    "\"type\": 1004," +
//                    "\"subtype\": 1004," +
//                    "\"did\": \"867725032979092\"," +
//                    "\"cmdid\": \"10001\"," +
//                    "\"data\": {" +
//                    "\"svrip\": \" 192.168.8.235 \"," +
//                    "\"hostnum\": 111000," +
//                    "\"ternum\": 111001" +
//                    "}" +
//                    "}" +
//                    "}";
            String exchange = map.get("exchange").toString();
            String routingkey = map.get("routingkey").toString();
            Object json = map.get("json");
        String jsonstr = JsonConvertBeanUtil.bean2json(json);
            this.rabbitTemplate.convertAndSend(exchange,routingkey,jsonstr);

    }
}