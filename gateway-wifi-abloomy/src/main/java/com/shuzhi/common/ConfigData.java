package com.shuzhi.common;


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

    //探针监听端口
    private int udpPort;

    //探针命令码
    private String wifiStyletId;

    //服务ip
    private String ip;
    //服务port
    private String port;
    //账号hash
    private String userhash;
    //命令回执消息类型
    private int msgtypeCommandReturn;
    //命令上报消息类型
    private int msgtypeReportMsg;
    //系统id
    private int systype;
    //区域id
    private int sysid;
    //链路id
    private int connectid;

    public String getWifiStyletId() {
        return wifiStyletId;
    }

    public void setWifiStyletId(String wifiStyletId) {
        this.wifiStyletId = wifiStyletId;
    }

    public int getMsgtypeCommandReturn() {
        return msgtypeCommandReturn;
    }

    public void setMsgtypeCommandReturn(int msgtypeCommandReturn) {
        this.msgtypeCommandReturn = msgtypeCommandReturn;
    }

    public int getMsgtypeReportMsg() {
        return msgtypeReportMsg;
    }

    public void setMsgtypeReportMsg(int msgtypeReportMsg) {
        this.msgtypeReportMsg = msgtypeReportMsg;
    }

    public int getSystype() {
        return systype;
    }

    public void setSystype(int systype) {
        this.systype = systype;
    }

    public int getSysid() {
        return sysid;
    }

    public void setSysid(int sysid) {
        this.sysid = sysid;
    }

    public int getConnectid() {
        return connectid;
    }

    public void setConnectid(int connectid) {
        this.connectid = connectid;
    }

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUserhash() {
        return userhash;
    }

    public void setUserhash(String userhash) {
        this.userhash = userhash;
    }

    public int getUdpPort() {
        return udpPort;
    }

    public void setUdpPort(int udpPort) {
        this.udpPort = udpPort;
    }
}
