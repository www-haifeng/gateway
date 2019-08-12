package com.shuzhi.common;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Description: 全局配置
 * @Author: lirb
 * @CreateDate: 2019/7/23 13:34
 * @Version: 1.0
 **/
@Component
@ConfigurationProperties(prefix = "factory")
@PropertySource(value = "classpath:factory.properties", encoding = "utf-8")
public class ConfigData {
    //设备厂商 匹配数据库字段
    private String name;

    //厂商设备类型
    private String typeName;

    //sign中的key
    private String key;

    //命令回执使用 成功命令码 为com.shuzhi.entity.MessageRevertData 中code提供
    private int seccussCode;
    //命令回执使用 失败命令码 为com.shuzhi.entity.MessageRevertData 中code提供
    private int failedCode;

    //命令回执消息类型
    private int msgtypeCommandReturn;

    //标识用户的身份
    private String partnerId;

    //用户名和密码 获取访问权限
    private String userName;
    private String password;

    //链路类型
    private String typeGroupCode;

    //系统类型
    private int sysType;

    //用于上报的infoid
    private String infoId;

    //用于上报的 commandId ，多个用分号分割
    private String reportCommandId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTypeGroupCode() {
        return typeGroupCode;
    }

    public void setTypeGroupCode(String typeGroupCode) {
        this.typeGroupCode = typeGroupCode;
    }

    public int getSysType() {
        return sysType;
    }

    public void setSysType(int sysType) {
        this.sysType = sysType;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getReportCommandId() {
        return reportCommandId;
    }

    public void setReportCommandId(String reportCommandId) {
        this.reportCommandId = reportCommandId;
    }
}
