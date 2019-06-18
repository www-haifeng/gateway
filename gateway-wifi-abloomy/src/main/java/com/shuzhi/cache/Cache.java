package com.shuzhi.cache;

import com.alibaba.fastjson.JSONArray;
import com.shuzhi.entity.CommandInfo;
import com.shuzhi.entity.DeviceInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:缓存对象
 * @Author: YHF
 * @date 17:01
 */
public class Cache {
    //请求令牌
    public static String accesstoken;

    //探针数据jsonArr
    public static JSONArray wifi = new JSONArray();

    //设备信息缓存 Key:did
    public static Map<String, DeviceInfo> deviceInfoMap = new ConcurrentHashMap<>();
    //设备命令缓存 Key:cmdid
    public static Map<String, CommandInfo> commandMap = new ConcurrentHashMap<>();
}
