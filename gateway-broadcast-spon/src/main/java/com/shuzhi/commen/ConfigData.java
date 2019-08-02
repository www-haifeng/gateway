package com.shuzhi.commen;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="factory")
@PropertySource(value="classpath:factory.properties",encoding = "utf-8")
public class ConfigData {
    //设备厂商 匹配数据库字段
    private String name;

    //sign中的key
    private String key;

    //命令回执使用 成功命令码 为com.shuzhi.entity.MessageRevertData 中code提供
    private int seccussCode;
    //命令回执使用 失败命令码 为com.shuzhi.entity.MessageRevertData 中code提供
    private int failedCode;

    //命令回执消息类型
    private int msgtypeCommandReturn;

    //链路类型
    private String typeGroupCode;

    //系统类型
    private int sysType;

    //添加媒体文件ftp地址
    private String ftpIp;

    //添加媒体文件ftp端口
    private int ftpPort;

    //添加媒体文件ftp用户名
    private String ftpName;

    //添加媒体文件ftp密码
    private String ftpPassword;

    //msgInfo 上报返回msg层使用
    private String infoId;

    //上报请求的url的 msgid
    private String reportMsgId;
}
