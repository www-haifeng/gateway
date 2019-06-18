package com.shuzhi.controller;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuzhi.cache.Cache;
import com.shuzhi.common.ConfigData;
import com.shuzhi.common.HttpCommandUtils;
import com.shuzhi.dao.CommandInfoDao;
import com.shuzhi.dao.DeviceInfoDao;
import com.shuzhi.entity.CommandInfo;
import com.shuzhi.entity.DeviceInfo;
import com.shuzhi.netty.NettyServerUDP;
import com.shuzhi.webSocket.WiFiWebSocketClient;
import org.java_websocket.WebSocket;
import org.java_websocket.enums.ReadyState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyStore;
import java.util.List;

/*
 * @Description:启动时加载数据库数据到缓存，以便翻译解析协议
 * @Author: YHF
 * @date 16:57
 */
@Component
@EnableConfigurationProperties(ConfigData.class)
@Order(1)
public class LoadData implements ApplicationRunner {
    private final static Logger logger = LoggerFactory.getLogger(LoadData.class);
    @Autowired
    private DeviceInfoDao deviceInfoDao;
    @Autowired
    private CommandInfoDao commandInfoDao;
    @Autowired
    private ConfigData configData;
    @Autowired
    private HttpCommandUtils httpCommandUtils;
    /**
     *功能描述 初始化缓存
     * @author YHF
     * @date 16:57
     * @params
     * @return void
     */
      @Override
  public void run(ApplicationArguments args) throws Exception {
          //初始化令牌
          httpCommandUtils.getAccessToken();
          logger.info("初始化令牌完毕");
        //加载设备信息缓存
        List<DeviceInfo> deviceInfos = deviceInfoDao.findDeviceInfo();
          for (DeviceInfo info : deviceInfos) {
              Cache.deviceInfoMap.put(info.getTdeviceAbloomyEntity().getMac(),info);
          }
          logger.info("设备信息缓存初始化完毕");
          //加载命令信息缓存
          List<CommandInfo> commandInfos = commandInfoDao.findcommandInfo(configData.getName());
          for (CommandInfo info : commandInfos) {
              Cache.commandMap.put(info.getTmsgInfoEntity().getMsgId(),info);
          }
          new NettyServerUDP().run(configData.getUdpPort());
      }


}
