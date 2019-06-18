package com.shuzhi.thread.scheduler;

import com.shuzhi.cache.Cache;
import com.shuzhi.common.ConfigData;
import com.shuzhi.common.HttpCommandUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
* @Program:  获取accesstoken
* @Description: 
* @Author: YHF
* @Create: 2019/6/10 19:25
**/
@Slf4j
@Component
@EnableConfigurationProperties(ConfigData.class)
public class SchedulerHeartBeatTask {
    @Autowired
    private HttpCommandUtils httpCommandUtils;
    @Scheduled(fixedRate = 1750000)
    private void process(){
        httpCommandUtils.getAccessToken();
    }
}
