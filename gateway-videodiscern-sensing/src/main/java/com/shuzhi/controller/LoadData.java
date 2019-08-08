package com.shuzhi.controller;

import com.shuzhi.cache.Cache;
import com.shuzhi.common.ConfigData;
import com.shuzhi.dao.CommandInfoDao;
import com.shuzhi.dao.GatewayConfigDao;
import com.shuzhi.entity.CommandInfo;
import com.shuzhi.entity.TGatewayConfigEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 启动时加载数据库数据到缓存
 * @Author: lirb
 * @CreateDate: 2019/7/23 13:36
 * @Version: 1.0
 **/
@Component
@EnableConfigurationProperties(ConfigData.class)
@Order(1)
public class LoadData implements ApplicationRunner {
    private final static Logger logger = LoggerFactory.getLogger(LoadData.class);

    @Autowired
    private CommandInfoDao commandInfoDao;
    @Autowired
    private GatewayConfigDao gatewayConfigDao;

    @Autowired
    ConfigData configData;

    /**
     * 初始化缓存
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {

        //加载命令信息缓存
        List<CommandInfo> commandInfos = commandInfoDao.findcommandInfo(configData.getTypeName(), configData.getName());
        for (CommandInfo info : commandInfos) {
            Cache.commandMap.put(info.getTmsgInfoEntity().getMsgId(), info);
        }
        logger.info("命令信息缓存初始化完毕");
        //加载网关链路信息缓存
        TGatewayConfigEntity gatewayConfigEntity = gatewayConfigDao.findByTypeGroupCode(configData.getTypeGroupCode());
        Cache.gatewayConfigEntity = gatewayConfigEntity;
        logger.info("链路信息缓存初始化完毕");
    }
}
