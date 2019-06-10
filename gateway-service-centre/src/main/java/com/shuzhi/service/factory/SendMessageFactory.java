package com.shuzhi.service.factory;

import com.shuzhi.conusmer.WSClientService;
import com.shuzhi.entity.TGatewayConfigEntity;
import com.shuzhi.entity.WebSocketEntity;
import com.shuzhi.pojo.MessagePojo;
import com.shuzhi.thread.FixedThreadPool;
import com.shuzhi.thread.handle.ExecuteThread;
import com.shuzhi.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
* @Program: 发送消息工厂
* @Description:
* @Author: YuJQ
* @Create: 2019/6/6 10:24
**/
@Slf4j
@Service
public class SendMessageFactory {
    @Autowired
    private FirstAllianceFactory firstAllianceFactory;
    @Value("${gateway.key}")
    private String key ;

    public void sendMessage(WebSocketEntity wsEntity){
                if(!wsEntity.getFirstAllianceConnection()){
                    //1.发送第一次首次建联消息
                    if(SessionManage.sessionStatus(wsEntity.getSession())) {
                    // 存活时候 发送首次建联
                        try {
                            wsEntity.setFirstAllianceConnection(Boolean.TRUE);
                            MessagePojo messagePojo = MessageFactoryModelUtil.messagePojoModel(key, ConstantUtils.KYE_5,wsEntity.getTGatewayConfigEntity().getSysType(),wsEntity.getTGatewayConfigEntity().getSysId(),wsEntity.getTGatewayConfigEntity().getConnectId());
                            wsEntity.setSendMessage(JsonConvertBeanUtil.bean2json(messagePojo));
                            ExecutorService executorService=FixedThreadPool.getThreadChannelCache(wsEntity.getTGatewayConfigEntity().getTypeGroupCode());
                            ExecuteThread.executeSendThread(executorService,wsEntity);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }else {
                     //不用移除 继续首次建联
                        String url = StringUtil.webSocketUrl(wsEntity.getTGatewayConfigEntity().getIp(),wsEntity.getTGatewayConfigEntity().getPort(),wsEntity.getTGatewayConfigEntity().getSocketName());
                        firstAllianceFactory.firstAllianceConnection( wsEntity.getTGatewayConfigEntity().getTypeGroupCode(),WSClientService.class,url,wsEntity.getTGatewayConfigEntity());
                    }
                }else {
                    //2.开始发送对应消息







                }








            //1.判断是否发送首次建联消息了
        }



    }


