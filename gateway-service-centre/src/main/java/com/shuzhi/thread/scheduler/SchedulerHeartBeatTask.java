package com.shuzhi.thread.scheduler;

import com.shuzhi.conusmer.WSClientService;
import com.shuzhi.entity.TGatewayConfigEntity;
import com.shuzhi.entity.WebSocketEntity;
import com.shuzhi.pojo.MessagePojo;
import com.shuzhi.service.factory.FirstAllianceFactory;
import com.shuzhi.thread.FixedThreadPool;
import com.shuzhi.thread.handle.ExecuteThread;
import com.shuzhi.thread.handle.TaskThread;
import com.shuzhi.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
* @Program:  心跳定时器SchedulerHeartBeatTask
* @Description: 
* @Author: YuJQ
* @Create: 2019/6/10 19:25
**/
@Slf4j
@Component
public class SchedulerHeartBeatTask {
    @Value("${gateway.key}")
    private String key ;
    @Autowired
    private FirstAllianceFactory firstAllianceFactory;

    @Scheduled(cron = "${scheduled.job.schedulerheartbeat}")
    private  void process(){

        Map<String, String> sessionChannelCaches = SessionRepository.getsessionChannelCaches();
        Map<String, Object> channelCaches =SessionRepository.getchannelCaches();
        if(!sessionChannelCaches.isEmpty() && !channelCaches.isEmpty()){
            for (Map.Entry<String, String> map: sessionChannelCaches.entrySet()) {
                WebSocketEntity wsEntity = (WebSocketEntity) channelCaches.get(map.getValue());
               //首次建联成功,可以发送心跳包
                if(wsEntity.getFirstAllianceConnection()){
                    MessagePojo messagePojo = MessageFactoryModelUtil.messagePojoModel(key, ConstantUtils.KYE_0,wsEntity.getTGatewayConfigEntity().getSysType(),wsEntity.getTGatewayConfigEntity().getSysId(),wsEntity.getTGatewayConfigEntity().getConnectId());
                    wsEntity.setMessage(JsonConvertBeanUtil.bean2json(messagePojo));
                    ExecutorService executorService= FixedThreadPool.getThreadChannelCache(wsEntity.getTGatewayConfigEntity().getTypeGroupCode());

                    //Future<Boolean> future = executorService.submit(new TaskThread(wsEntity));
                    try {
                        //发送判断
                        //回执校验判断
                        boolean flug = ExecuteThread.executeSendThread(executorService,wsEntity);
                        log.info("心跳线程发送结果 : " +flug);
                        if (!flug) {
                            TGatewayConfigEntity tGatewayConfigEntity = wsEntity.getTGatewayConfigEntity();
                            //没有发送成功   发送重新连接请求
                            String url = StringUtil.webSocketUrl(tGatewayConfigEntity.getIp(),tGatewayConfigEntity.getPort(),tGatewayConfigEntity.getSocketName());
                            firstAllianceFactory.firstAllianceConnection(tGatewayConfigEntity.getTypeGroupCode(),WSClientService.class,url,tGatewayConfigEntity,map.getKey());
                            log.info("发送心跳失败,重新建立请求.");
                        }
                    }  catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                    }
            }
        }
    }

