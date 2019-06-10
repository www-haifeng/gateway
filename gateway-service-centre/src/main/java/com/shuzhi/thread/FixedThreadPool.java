package com.shuzhi.thread;

import com.shuzhi.enums.TypeGropCodeEnums;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* @Program: 创建线程池
* @Description:
* @Author: YuJQ
* @Create: 2019/6/10 14:49
**/
public class FixedThreadPool {

    //一键告警通道
    public static final ExecutorService alarmMessageThreadPool = Executors.newSingleThreadExecutor();
    //下控通道
    public static final ExecutorService lowerControlMessageThreadPool = Executors.newSingleThreadExecutor();
    //wifi通道
    public static final ExecutorService wifiMessageThreadPool = Executors.newSingleThreadExecutor();
    //上报通道
    public static final ExecutorService upMessageThreadPool = Executors.newSingleThreadExecutor();


    private final static Map<String, ExecutorService> threadChannelCache = new ConcurrentHashMap<String, ExecutorService>(){
        {
            put(String.valueOf(TypeGropCodeEnums.lowerControlMessage.getCode()),lowerControlMessageThreadPool);
            put(String.valueOf(TypeGropCodeEnums.alarmMessage.getCode()),alarmMessageThreadPool);
            put(String.valueOf(TypeGropCodeEnums.upMessage.getCode()),upMessageThreadPool);
            put(String.valueOf(TypeGropCodeEnums.wifiMessage.getCode()),wifiMessageThreadPool);
        }
    };

    public static ExecutorService getThreadChannelCache(String key){
        return threadChannelCache.get(key);
    }

}
