package com.shuzhi.conusmer;

import com.shuzhi.entity.TGatewayConfigEntity;
import com.shuzhi.entity.WebSocketEntity;
import com.shuzhi.pojo.MessagePojo;
import com.shuzhi.service.factory.FirstAllianceFactory;
import com.shuzhi.service.factory.SendMessageFactory;
import com.shuzhi.thread.scheduler.SchedulerSendFailTask;
import com.shuzhi.util.*;
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
        WebSocketEntity entity = null;
        try {
            //获取相应的通道
            String code =  SessionRepository.getSessionChannelCache(session.getId());
             entity = (WebSocketEntity) SessionRepository.getChannelCache(code);
            entity.setMessage(message);
            MessagePojo messagePojo = (MessagePojo) JsonConvertBeanUtil.json2Object(message, MessagePojo.class);

            if (ConstantUtils.KYE_2 ==Integer.parseInt(messagePojo.getMsgtype())) {
                //收到回执  删除缓存
                if(SessionRepository.sendFailCache.get(messagePojo.getMsgid().hashCode()) != null){
                    WebSocketEntity wsEntity = (WebSocketEntity) SessionRepository.sendFailCache.get(messagePojo.getMsgid().hashCode());
                    if(!SchedulerSendFailTask.timeComparison(wsEntity.getSendTimeStamp(),wsEntity.getExpiresTimeStamp())){
                        SessionRepository.sendFailCache.remove(messagePojo.getMsgid().hashCode());
                    }
                }
            } else if(ConstantUtils.KYE_1 ==Integer.parseInt(messagePojo.getMsgtype())) {
                sendMessageFactory.sendMessage(entity);
            }
            log.info("接收客户端消息" + message);
        } catch (NumberFormatException e) {
            log.info("服务端发送的消息失败是 : " + message +" , 消息通道对象是 : "+entity);
        }
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
        //String url = StringUtil.webSocketUrl(tGatewayConfigEntity.getIp(),tGatewayConfigEntity.getPort(),tGatewayConfigEntity.getSocketName());
        //firstAllianceFactory.firstAllianceConnection(tGatewayConfigEntity.getTypeGroupCode(),WSClientService.class,url,tGatewayConfigEntity,session.getId());

        log.info("OnClose服务端关闭连接" +session.getId());


    }
}
