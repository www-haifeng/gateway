package com.shuzhi.commen;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getSeccussCode() {
        return seccussCode;
    }

    public void setSeccussCode(int seccussCode) {
        this.seccussCode = seccussCode;
    }

    public int getFailedCode() {
        return failedCode;
    }

    public void setFailedCode(int failedCode) {
        this.failedCode = failedCode;
    }

    public int getMsgtypeCommandReturn() {
        return msgtypeCommandReturn;
    }

    public void setMsgtypeCommandReturn(int msgtypeCommandReturn) {
        this.msgtypeCommandReturn = msgtypeCommandReturn;
    }
}
