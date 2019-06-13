package com.shuzhi.service.factory;

import com.shuzhi.conusmer.WSClientService;
import com.shuzhi.entity.TGatewayConfigEntity;
import com.shuzhi.entity.WebSocketEntity;
import com.shuzhi.util.SessionRepository;
import com.shuzhi.util.StringUtil;
import com.shuzhi.util.WebSocketUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.Session;

/**
* @Program: 首次建联
* @Description:
* @Author: YuJQ
* @Create: 2019/6/6 10:23
**/
@Slf4j
@Service
public class FirstAllianceFactory {

    @Autowired
    private SendMessageFactory sendMessageFactory;
    /**
    * @Description: 首次建联
    * @Param : 
    * @Author: YuJQ
    * @Date : 2019/6/6 10:51
    */
    public void firstAllianceConnection(String typeGroupCode,Class clazz,String url,TGatewayConfigEntity tGatewayConfigEntity,String sessionId){
        Session session = WebSocketUtil.socketClientCreate(WSClientService.class, url);
        log.info("首次建立session返回结果"+session);
        if(session != null){
            if(StringUtil.isNotEmpty(sessionId)){
                SessionRepository.removeCaches(sessionId);
            }

            WebSocketEntity entity = new WebSocketEntity();
            entity.setSession(session);
            entity.setTGatewayConfigEntity(tGatewayConfigEntity);
            SessionRepository.putChannelCache(typeGroupCode,entity);
            SessionRepository.putSessionChannelCache(session.getId(),typeGroupCode);


            //通知发送消息 发送首次建联消息
            sendMessageFactory.sendMessage(entity);

        }



    }
}
