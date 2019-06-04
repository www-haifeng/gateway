package com.shuzhi;

import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
* @Program: ChannelRepository
* @Description: 
* @Author: YuJQ
* @Create: 2019/4/10 10:39
**/
public class ChannelRepository {
	private final static Map<String, Channel> channelCache = new ConcurrentHashMap<String, Channel>();
//	private final static Deque<Channel> channelCacheList = new ConcurrentLinkedDeque<Channel>();
	public void put(String key, Channel value) {
		channelCache.put(key, value);
	}

	public Channel get(String key) {
		return channelCache.get(key);
	}

	public void remove(String key) { 
		channelCache.remove(key);
	}

	public int size() {
		return channelCache.size();
	}

	public List<Channel> getValues(){
		List<Channel> list =new ArrayList<Channel>();
		for (Map.Entry<String, Channel> map:channelCache.entrySet()) {
			list.add(map.getValue());
		}
			return list;
	}
}
