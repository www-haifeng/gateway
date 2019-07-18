package com.shuzhi.cache;


import com.shuzhi.entity.TDeviceFactoryCronEntity;
import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 全局缓存
 */
public class Cache {

    //第一条结果集的缓存
    public static byte[] sendOneByte;

    //缓存客户端channel
    public static Map<String, ChannelHandlerContext> channelCache = new ConcurrentHashMap<String, ChannelHandlerContext>();

    //frt 定时任务缓存
    public static TDeviceFactoryCronEntity cronEntity ;

    //设备信息缓存 Key:did value：deviceId
    public static Map<String, String> deviceInfoMap = new ConcurrentHashMap<>();
}
