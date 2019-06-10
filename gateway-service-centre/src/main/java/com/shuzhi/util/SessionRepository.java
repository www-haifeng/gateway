package com.shuzhi.util;

import io.netty.channel.Channel;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
* @Program: SessionRepository保存会话
* @Description: 
* @Author: YuJQ
* @Create: 2019/4/10 10:39
**/
public class SessionRepository {
	//<上行通道code,session>
	private final static Map<String, Object> channelCache = new ConcurrentHashMap<String, Object>();
	//<websocket sessioncode,上行通道code>
	private final static Map<String, String> sessionChannelCache = new ConcurrentHashMap<String, String>();


	public static void putChannelCache(String key, Object value) { channelCache.put(key, value); }
	public static Object getChannelCache(String key) {
		return channelCache.get(key);
	}
	public static void removeChannelCache(String key) {
		channelCache.remove(key);
	}
	public static int sizeChannelCache() {
		return channelCache.size();
	}


	public static void putSessionChannelCache(String key, String value) {
		sessionChannelCache.put(key, value);
	}
	public static String getSessionChannelCache(String key) {
		return sessionChannelCache.get(key);
	}
	public static void removeSessionChannelCache(String key) {
		sessionChannelCache.remove(key);
	}
	public static int sizeSessionChannelCache() {
		return sessionChannelCache.size();
	}


	public static List<Object> getchannelCacheValues(){
		List<Object> list =new ArrayList<Object>();
		for (Map.Entry<String, Object> map:channelCache.entrySet()) {
			list.add(map.getValue());
		}
			return list;
	}
	public static Map<String, Object> getchannelCaches(){
		return channelCache;
	}
	public static List<String> getsessionChannelCacheValues(){
		List<String> list =new ArrayList<String>();
		for (Map.Entry<String, String> map:sessionChannelCache.entrySet()) {
			list.add(map.getValue());
		}
		return list;
	}
	public static Map<String, String> getsessionChannelCaches(){
		return sessionChannelCache;
	}


	public static void removeCaches(String sessionId){
		String channelKey = SessionRepository.getSessionChannelCache(sessionId);
		SessionRepository.removeChannelCache(channelKey);
		SessionRepository.removeSessionChannelCache(sessionId);
	}
}
