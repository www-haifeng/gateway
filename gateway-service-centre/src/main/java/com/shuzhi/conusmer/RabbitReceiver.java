package com.shuzhi.conusmer;

import com.rabbitmq.client.Channel;
import com.shuzhi.entity.WebSocketEntity;
import com.shuzhi.enums.TypeGropCodeEnums;
import com.shuzhi.service.factory.SendMessageFactory;
import com.shuzhi.util.SessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;

import org.springframework.stereotype.Component;


/**
* @Program: rabbitmq接收方法
* @Description: 
* @Author: YuJQ
* @Create: 2019/6/4 16:40
**/
@Slf4j
@Component
public class RabbitReceiver {
	@Autowired
	private SendMessageFactory sendMessageFactory;
	/**
	 * @Description: 监听上报主题
	 * @Param :
	 * @Author: YuJQ
	 * @Date : 2019/6/4 17:25
	 */
	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(value = "upMessage",
					durable="true"),
			exchange = @Exchange(value = "upMessage",
					durable="true",
					type= "topic",
					ignoreDeclarationExceptions = "true"),
			key = "upMessage"
	)
	)
	@RabbitHandler
	public void upMessage(Message message, Channel channel) throws Exception {


			log.info("收到上报主题"+message.getPayload().toString());
			Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
			//手工ACK
			channel.basicAck(deliveryTag, false);
		try {
			WebSocketEntity entity = (WebSocketEntity) SessionRepository.getChannelCache(String.valueOf(TypeGropCodeEnums.upMessage.getCode()));
			if(entity != null){
				entity.setMessage(message.getPayload().toString());
				sendMessageFactory.sendMessage(entity);
			}
		}catch (Exception e){

		}
	}
	/**
	 * @Description: 监听下控主题
	 * @Param :
	 * @Author: YuJQ
	 * @Date : 2019/6/4 17:25
	 */
	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(value = "lowerControlMessage",
					durable="true"),
			exchange = @Exchange(value = "lowerControlMessage",
					durable="true",
					type= "topic",
					ignoreDeclarationExceptions = "true"),
			key = "lowerControlMessage"
	)
	)
	@RabbitHandler
	public void lowerControlMessage(Message message, Channel channel) throws Exception {

			log.info("监听下控主题"+message.getPayload().toString());
			Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
			//手工ACK
			channel.basicAck(deliveryTag, false);
		try {
			WebSocketEntity entity = (WebSocketEntity) SessionRepository.getChannelCache(String.valueOf(TypeGropCodeEnums.lowerControlMessage.getCode()));
			if(entity != null){
				entity.setMessage(message.getPayload().toString());
				sendMessageFactory.sendMessage(entity);
			}


		}catch (Exception e){

		}
	}
	/**
	 * @Description: 监听WIFI主题
	 * @Param :
	 * @Author: YuJQ
	 * @Date : 2019/6/4 17:25
	 */
	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(value = "wifiMessage",
					durable="true"),
			exchange = @Exchange(value = "wifiMessage",
					durable="true",
					type= "topic",
					ignoreDeclarationExceptions = "true"),
			key = "wifiMessage"
	)
	)
	@RabbitHandler
	public void wifiMessage(Message message, Channel channel) throws Exception {

			log.info("监听WIFI主题"+message.getPayload().toString());
			Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
			//手工ACK
			channel.basicAck(deliveryTag, false);
		try {
			WebSocketEntity entity = (WebSocketEntity) SessionRepository.getChannelCache(String.valueOf(TypeGropCodeEnums.wifiMessage.getCode()));
			if(entity != null){
				entity.setMessage(message.getPayload().toString());
				sendMessageFactory.sendMessage(entity);
			}

		}catch (Exception e){

		}
	}
	/**
	 * @Description: 监听告警主题
	 * @Param :
	 * @Author: YuJQ
	 * @Date : 2019/6/4 17:25
	 */
	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(value = "alarmMessage",
					durable="true"),
			exchange = @Exchange(value = "alarmMessage",
					durable="true",
					type= "topic",
					ignoreDeclarationExceptions = "true"),
			key = "alarmMessage"
	)
	)
	@RabbitHandler
	public void alarmMessage(Message message, Channel channel) throws Exception {

			log.info("监听告警主题"+message.getPayload().toString());
			Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
			System.out.println(deliveryTag+"=================channel"+channel);
			//手工ACK
			channel.basicAck(deliveryTag, false);
		try {
			WebSocketEntity entity = (WebSocketEntity) SessionRepository.getChannelCache(String.valueOf(TypeGropCodeEnums.alarmMessage.getCode()));
			if(entity != null){
				entity.setMessage(message.getPayload().toString());
				sendMessageFactory.sendMessage(entity);
			}

		}catch (Exception e){

		}
	}
}
