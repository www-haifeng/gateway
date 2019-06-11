package com.shuzhi.conusmer;

import com.shuzhi.entity.TGatewayConfigEntity;
import com.shuzhi.entity.WebSocketEntity;
import com.shuzhi.service.factory.FirstAllianceFactory;
import com.shuzhi.service.factory.SendMessageFactory;
import com.shuzhi.util.SessionRepository;
import com.shuzhi.util.SpringUtil;
import com.shuzhi.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.websocket.*;

/**
* @Program: websocket客户端连接上层服务
* @Description: 
* @Author: YuJQ
* @Create: 2019/6/5 10:38
**/
@Slf4j
@ClientEndpoint
public class WSClientService {

    private SendMessageFactory sendMessageFactory = SpringUtil.getBean(SendMessageFactory.class);
    private FirstAllianceFactory firstAllianceFactory = SpringUtil.getBean(FirstAllianceFactory.class);
    @OnOpen
    public void onOpen(Session session) {
        log.info("客户端建立连接成功");
    }

    @OnMessage
    public void onMessage(Session session,String message) {
        //获取相应的通道
       String code =  SessionRepository.getSessionChannelCache(session.getId());
        WebSocketEntity entity = (WebSocketEntity) SessionRepository.getChannelCache(code);
        entity.setMessage(message);
        sendMessageFactory.sendMessage(entity);
        log.info("接收客户端消息" + message);

    }

    @OnClose
    public void onClose(Session session) {
        //先获取entity 信息
        String code =  SessionRepository.getSessionChannelCache(session.getId());
        WebSocketEntity entity = (WebSocketEntity) SessionRepository.getChannelCache(code);
        TGatewayConfigEntity tGatewayConfigEntity = entity.getTGatewayConfigEntity();
//        //删除缓存map
//        SessionRepository.removeCaches(session.getId());
        //重新建立连接
        String url = StringUtil.webSocketUrl(tGatewayConfigEntity.getIp(),tGatewayConfigEntity.getPort(),tGatewayConfigEntity.getSocketName());
        firstAllianceFactory.firstAllianceConnection(tGatewayConfigEntity.getTypeGroupCode(),WSClientService.class,url,tGatewayConfigEntity,session.getId());

        log.info("服务端关闭后重新连接");


    }
}
