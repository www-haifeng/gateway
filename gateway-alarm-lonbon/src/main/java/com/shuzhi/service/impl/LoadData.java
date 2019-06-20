package com.shuzhi.service.impl;

import com.shuzhi.cache.Cache;
import com.shuzhi.dao.CommandInfoDao;
import com.shuzhi.dao.DeviceInfoDao;
import com.shuzhi.entity.CommandInfo;
import com.shuzhi.entity.DeviceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/*
 * @Description:启动时加载数据库数据到缓存，以便翻译解析协议
 * @Author: ztt
 * @date 16:57
 */
@Component
@Order(1)
public class LoadData implements ApplicationRunner {
    private final static Logger logger = LoggerFactory.getLogger(LoadData.class);
    @Autowired
    public DeviceInfoDao deviceInfoDao;
    @Autowired
    public CommandInfoDao commandInfoDao;

    /**
     *功能描述 初始化缓存
     * @Author: ztt
     * @date 16:57
     * @params
     * @return void
     */
      @Override
  public void run(ApplicationArguments args) throws Exception {
          //加载设备信息缓存
          List<DeviceInfo> deviceInfos = deviceInfoDao.findDeviceInfo();
          for (DeviceInfo info : deviceInfos) {
              Cache.deviceInfoMap.put(info.getTdeviceLonBonEntity().getDid(),info);
          }
          logger.info("设备信息缓存初始化完毕");
          //加载命令信息缓存
          List<CommandInfo> commandInfos = commandInfoDao.findcommandInfo("来邦");
          for (CommandInfo info : commandInfos) {
              Cache.commandMap.put(info.getTmsgInfoEntity().getMsgId(),info);
          }
          logger.info("命令信息缓存初始化完毕");
      }
}
