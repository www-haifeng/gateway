package com.shuzhi.cache;

import com.shuzhi.entity.CommandInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:缓存对象
 * @Author: YHF
 * @date 17:01
 */
public class Cache {

    //设备命令缓存 Key:cmdid
    public static Map<String, CommandInfo> commandMap = new ConcurrentHashMap<>();

}
