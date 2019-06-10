package com.shuzhi;

import com.shuzhi.conusmer.WSClientService;
import com.shuzhi.entity.TGatewayConfigEntity;
import com.shuzhi.service.TGatewayConfigService;
import com.shuzhi.service.factory.FirstAllianceFactory;
import com.shuzhi.util.StringUtil;
import com.shuzhi.util.WebSocketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* @Program:
* @Description:创建websocket与服务端连接
* @Author: YuJQ
* @Create: 2019/6/5 16:30
**/
@Component
@Order(2)
public class WBApplicationRunner implements ApplicationRunner {

	@Autowired
	private TGatewayConfigService tGatewayConfigService;
	@Autowired
	private FirstAllianceFactory firstAllianceFactory;



	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		List<TGatewayConfigEntity> list = tGatewayConfigService.findall();
		if(!list.isEmpty()){
			//1.建立websocket 连接
			for (TGatewayConfigEntity t :list) {
				if(StringUtil.isEmpty(t.getIp()) &&StringUtil.isEmpty(t.getSocketName())&& t.getPort() != null){
					String url = StringUtil.webSocketUrl(t.getIp(),t.getPort(),t.getSocketName());
					//WebSocketUtil.socketClientCreate(WSClientService.class,url);
					firstAllianceFactory.firstAllianceConnection(t.getTypeGroupCode(),WSClientService.class,url,t);
				}
			}

			//2.发送首次建联
			//3.定时发送心跳包
			//4.收取下控命令 发送mq

		}
		//kafkaClientIot.doStartProcessKafkaData();
	}

}
