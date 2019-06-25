package com.shuzhi.thread.scheduler;

import com.shuzhi.conusmer.WSClientService;
import com.shuzhi.entity.TGatewayConfigEntity;
import com.shuzhi.entity.TReportingInforHistoryEntity;
import com.shuzhi.entity.WebSocketEntity;
import com.shuzhi.enums.TypeGropCodeEnums;
import com.shuzhi.pojo.MessagePojo;
import com.shuzhi.service.TReportingInforHistoryService;
import com.shuzhi.service.factory.FirstAllianceFactory;
import com.shuzhi.service.factory.SendMessageFactory;
import com.shuzhi.thread.FixedThreadPool;
import com.shuzhi.thread.handle.ExecuteThread;
import com.shuzhi.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
* @Program:
* @Description: 定时查询是否超时
* @Author: YuJQ
* @Create: 2019/6/10 19:25
**/
@Slf4j
@Component
public class SchedulerSendFailTask {
    @Autowired
    private TReportingInforHistoryService tReportingInforHistoryService;
    @Autowired
    private SendMessageFactory sendMessageFactory;

    @Scheduled(cron = "${scheduled.job.sendfail}")
    private void process(){


        Map<Integer, Object> sendFailCaches = SessionRepository.sendFailCache;

        if(!sendFailCaches.isEmpty() ){
            for (Map.Entry<Integer, Object> map: sendFailCaches.entrySet()) {
                WebSocketEntity wsEntity = (WebSocketEntity) map.getValue();
                //如果大于发送次数 就存入导数据库中
                if(!SchedulerSendFailTask.timeComparison(wsEntity.getSendTimeStamp(),ConstantUtils.EXPIRE_STIME) && !SchedulerSendFailTask.sendCount(wsEntity.getSendCount())  && !SchedulerSendFailTask.timeComparison(wsEntity.getSendTimeStamp(),wsEntity.getExpiresTimeStamp())){
                    TReportingInforHistoryEntity entity = SchedulerSendFailTask.getEntity(wsEntity);
                    tReportingInforHistoryService.save( entity);
                    sendFailCaches.remove(map.getKey());

                }else if(SchedulerSendFailTask.timeComparison(wsEntity.getSendTimeStamp(),ConstantUtils.EXPIRE_STIME) && SchedulerSendFailTask.sendCount(wsEntity.getSendCount())  && SchedulerSendFailTask.timeComparison(wsEntity.getSendTimeStamp(),wsEntity.getExpiresTimeStamp())){
                        sendMessageFactory.sendMessage(wsEntity);

                }
            }
        }
    }
    //为true 为超时, false 为 未超时
    public static  Boolean  timeComparison(Long startTime,Long expiresTimeStamp){
        return new Date().getTime() >= startTime+expiresTimeStamp ? true :false;
    }
    //为true  可以继续发送, 为false 达到发送次数 需要存数据库中
    private static  Boolean  sendCount(Integer sendCount){
        return sendCount < ConstantUtils.SEND_COUNT ? true :false;
    }


    public  static  void  checkMsg(Long startTime,Long expiresTimeStamp,Map<Integer, Object> sendFailCaches,MessagePojo messagePojo){
        if(SchedulerSendFailTask.timeComparison(startTime,expiresTimeStamp)){
            sendFailCaches.remove(messagePojo.getMsgid().hashCode());
        }

    }

    private static TReportingInforHistoryEntity getEntity( WebSocketEntity wsEntity){
        TGatewayConfigEntity tGatewayConfigEntity = wsEntity.getTGatewayConfigEntity();
        String  sysid = tGatewayConfigEntity.getSysId();
        String systype = tGatewayConfigEntity.getSysType();
        String msgType = tGatewayConfigEntity.getTypeGroupCode();
        String msgName =  tGatewayConfigEntity.getDescribe();
        String connectid =  tGatewayConfigEntity.getConnectId();
        String data = wsEntity.getMessage();


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
}
