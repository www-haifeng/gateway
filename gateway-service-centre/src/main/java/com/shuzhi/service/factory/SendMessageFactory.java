package com.shuzhi.service.factory;

import com.shuzhi.conusmer.WSClientService;
import com.shuzhi.dao.TDeviceFactoryEntityRepository;
import com.shuzhi.entity.TGatewayConfigEntity;
import com.shuzhi.entity.TReportingInforHistoryEntity;
import com.shuzhi.entity.WebSocketEntity;
import com.shuzhi.enums.MsgTypeEnums;
import com.shuzhi.pojo.MessagePojo;
import com.shuzhi.producer.RabbitSender;
import com.shuzhi.service.TReportingInforHistoryService;
import com.shuzhi.thread.FixedThreadPool;
import com.shuzhi.thread.handle.ExecuteThread;
import com.shuzhi.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.util.Date;
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
    private TReportingInforHistoryService tReportingInforHistoryService;

    @Autowired
    private RabbitSender rabbitSender;

    @Value("${gateway.key}")
    private String key ;

    public void sendMessage(WebSocketEntity wsEntity)  {
                if(!wsEntity.getFirstAllianceConnection()){
                    //1.发送第一次首次建联消息
                    if(SessionManage.sessionStatus(wsEntity.getSession())) {
                    // 存活时候 发送首次建联
                        try {
                            wsEntity.setFirstAllianceConnection(Boolean.TRUE);
                            MessagePojo messagePojo = MessageFactoryModelUtil.messagePojoModel(key, ConstantUtils.KYE_5,wsEntity.getTGatewayConfigEntity().getSysType(),wsEntity.getTGatewayConfigEntity().getSysId(),wsEntity.getTGatewayConfigEntity().getConnectId());
                            wsEntity.setMessage(JsonConvertBeanUtil.bean2json(messagePojo));
                            ExecutorService executorService=FixedThreadPool.getThreadChannelCache(wsEntity.getTGatewayConfigEntity().getTypeGroupCode());
                            ExecuteThread.executeSendThread(executorService,wsEntity);
                        } catch (Exception e) {
                           log.info("首次建联失败");
                        }
                    }
                }else {
                    if(SessionManage.sessionStatus(wsEntity.getSession())) {
                        MessagePojo messagePojo= (MessagePojo) JsonConvertBeanUtil.json2Object( wsEntity.getMessage(),MessagePojo.class);
                        //2.开始发送对应消息  如果是命令消息,回执后在放入mq中,设备在操作
                        if(messagePojo != null){
                            //下控
                            if(ConstantUtils.KYE_1 ==Integer.parseInt(messagePojo.getMsgtype()) ){
                                MessagePojo pojo = MessageFactoryModelUtil.messagePojoModel(key, ConstantUtils.KYE_2,wsEntity.getTGatewayConfigEntity().getSysType(),wsEntity.getTGatewayConfigEntity().getSysId(),wsEntity.getTGatewayConfigEntity().getConnectId());
                                wsEntity.setMessage(JsonConvertBeanUtil.bean2json(pojo));
                                ExecutorService executorService=FixedThreadPool.getThreadChannelCache(wsEntity.getTGatewayConfigEntity().getTypeGroupCode());
                                ExecuteThread.executeSendThread(executorService,wsEntity);
                                String exchange = SessionRepository.codeSocketNameKey(Integer.parseInt(pojo.getType()));
                                String topic = SessionRepository.codeSocketNameValue(Integer.parseInt(pojo.getType()));
                                //放入到mq中
                                try {
                                    rabbitSender.send(exchange,topic,wsEntity.getMessage());
                                    log.info("下控命令,发送mq成功,交换机="+exchange +" 主题="+topic+" 消息="+wsEntity.getMessage());
                                } catch (Exception e) {
                                    log.info("下控命令,发送mq失败,交换机="+exchange +" 主题="+topic+" 消息="+wsEntity.getMessage());
                                }
                            }else {
                                //不发控制命令 都是拼好发送
                               // try{
                                SendMessageFactory.addSendFailCache(wsEntity);
                                SessionRepository.sendFailCache.put(messagePojo.getMsgid().hashCode(),wsEntity);
                                ExecutorService executorService=FixedThreadPool.getThreadChannelCache(wsEntity.getTGatewayConfigEntity().getTypeGroupCode());
                                ExecuteThread.executeSendThread(executorService,wsEntity);
                            }
                        }
                    }
                }
        }

    private static TReportingInforHistoryEntity getEntity(String msgName,String sysid,String systype,String msgType ,String connectid,String data){
            TReportingInforHistoryEntity entity = new TReportingInforHistoryEntity();
            entity.setMsgName(msgName);
            entity.setSysid(sysid);
            entity.setSystype(systype);
            entity.setTimestamp(new Date());
            entity.setMsgType(msgType);
            entity.setConnectid(connectid);
            entity.setData(data);
            return entity;
        }

       private static void addSendFailCache(WebSocketEntity wsEntity) {
           wsEntity.setExpiresTimeStamp(ConstantUtils.EXPIRE_STIME_STAMP);
           wsEntity.setSendTimeStamp(new Date().getTime());
           wsEntity.setSendCount(wsEntity.getSendCount() +1);

    }

    }


