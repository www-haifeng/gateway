package com.shuzhi.consumer;

import com.rabbitmq.client.Channel;
import com.shuzhi.common.CommandUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


/**
* @Program: rabbitmq接收方法
* @Description: 
* @Author: YuJQ
* @Create: 2019/6/4 16:40
**/

@Component
@Order(2)
public class RabbitReceiver {
	private final static Logger logger = LoggerFactory.getLogger(RabbitReceiver.class);
	@Autowired
	private CommandUtils commandUtils;

	@RabbitListener(queues="spon")
	@RabbitHandler
	public void commandMessage(Message message, Channel channel) throws Exception {
		System.out.println("消费端Payload: " + message.getPayload());
		Long deliveryTag = (Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
		//手工ACK
		channel.basicAck(deliveryTag, false);
		commandUtils.commandSelect((String)message.getPayload());
	}
}
