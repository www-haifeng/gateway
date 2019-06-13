package com.shuzhi.thread.scheduler;

import com.shuzhi.conusmer.WSClientService;
import com.shuzhi.entity.TGatewayConfigEntity;
import com.shuzhi.entity.WebSocketEntity;
import com.shuzhi.pojo.MessagePojo;
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
    private SendMessageFactory sendMessageFactory;

    @Scheduled(cron = "${scheduled.job.sendfail}")
    private void process(){


        Map<Integer, Object> sendFailCaches = SessionRepository.sendFailCache;

        if(!sendFailCaches.isEmpty() ){
            for (Map.Entry<Integer, Object> map: sendFailCaches.entrySet()) {
                WebSocketEntity wsEntity = (WebSocketEntity) map.getValue();
                if( SchedulerSendFailTask.timeComparison(wsEntity.getSendTimeStamp(),ConstantUtils.EXPIRE_STIME) &&SchedulerSendFailTask.sendCount(wsEntity.getSendCount()) && SchedulerSendFailTask.timeComparison(wsEntity.getSendTimeStamp(),wsEntity.getExpiresTimeStamp())){
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
}
