package com.shuzhi.conusmer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;
/**
* @Program: rabbitmq接收方法
* @Description: 
* @Author: YuJQ
* @Create: 2019/6/4 16:40
**/

@Component
public class RabbitReceiver {

	/**
	 * @Description: 监听上报主题
	 * @Param :
	 * @Author: YuJQ
	 * @Date : 2019/6/4 17:25
	 */
	@RabbitListener(queues="upMessage")
	@RabbitHandler
	public void upMessage(Message message, Channel channel) throws Exception {
		System.err.println("--------------------------------------");
		System.err.println("消费端Payload: " + message.getPayload());
		Long deliveryTag = (Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
		//手工ACK
		channel.basicAck(deliveryTag, false);
	}
	/**
	 * @Description: 监听下控主题
	 * @Param :
	 * @Author: YuJQ
	 * @Date : 2019/6/4 17:25
	 */
	@RabbitListener(queues="lowerControlMessage")
	@RabbitHandler
	public void lowerControlMessage(Message message, Channel channel) throws Exception {
		System.err.println("--------------------------------------");
		System.err.println("消费端Payload: " + message.getPayload());
		Long deliveryTag = (Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
		//手工ACK
		channel.basicAck(deliveryTag, false);
	}
	/**
	 * @Description: 监听WIFI主题
	 * @Param :
	 * @Author: YuJQ
	 * @Date : 2019/6/4 17:25
	 */
	@RabbitListener(queues="wifiMessage")
	@RabbitHandler
	public void wifiMessage(Message message, Channel channel) throws Exception {
		System.err.println("--------------------------------------");
		System.err.println("消费端Payload: " + message.getPayload());
		Long deliveryTag = (Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
		//手工ACK
		channel.basicAck(deliveryTag, false);
	}
	/**
	 * @Description: 监听告警主题
	 * @Param :
	 * @Author: YuJQ
	 * @Date : 2019/6/4 17:25
	 */
	@RabbitListener(queues="alarmMessage")
	@RabbitHandler
	public void alarmMessage(Message message, Channel channel) throws Exception {
		System.err.println("--------------------------------------");
		System.err.println("消费端Payload: " + message.getPayload());
		Long deliveryTag = (Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
		//手工ACK
		channel.basicAck(deliveryTag, false);
	}
}
