package com.shuzhi.controller;

import com.shuzhi.common.ConfigData;
import com.shuzhi.netty.TimeServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableConfigurationProperties(ConfigData.class)
@Order(1)
public class LoadData implements ApplicationRunner {


    @Override
    public void run(ApplicationArguments args) throws Exception {

        new TimeServer().bind(8080);
    }

}
