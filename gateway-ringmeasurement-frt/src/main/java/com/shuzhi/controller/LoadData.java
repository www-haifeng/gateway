package com.shuzhi.controller;

import com.shuzhi.cache.Cache;
import com.shuzhi.common.ConfigData;
import com.shuzhi.dao.DeviceInfoDao;
import com.shuzhi.entity.DeviceInfo;
import com.shuzhi.netty.TimeServer;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@EnableConfigurationProperties(ConfigData.class)
@Order(1)
public class LoadData implements ApplicationRunner {
    private final static Logger logger = LoggerFactory.getLogger(LoadData.class);

    @Autowired
    private DeviceInfoDao deviceInfoDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //加载设备信息缓存
        List<DeviceInfo> deviceInfos = deviceInfoDao.findDeviceInfo();
        for (DeviceInfo info : deviceInfos) {
            Cache.deviceInfoMap.put(info.getTdeviceFrtEntity().getDid(),info.getTdeviceFrtEntity().getDeviceId());
        }
        logger.info("设备信息缓存初始化完毕");
        new TimeServer().bind(8080);
    }

}
