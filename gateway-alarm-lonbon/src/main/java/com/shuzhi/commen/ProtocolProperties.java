package com.shuzhi.commen;

import lombok.Data;
import org.apache.commons.net.ftp.FTP;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Description: 定时上报设备在线状态配置信息
 * @Author: YHF
 * @date 2019/7/31
 */
@Data
@Component
@PropertySource("classpath:protocol.properties")
@ConfigurationProperties(prefix = "protocol")
public class ProtocolProperties {
    private int runMsg;
    private int systype;
    private int sysid;
    private int connectid;
    private String infoidOnLine;
    private String infoidEvent;
    private String infoidUpLoad;

}


