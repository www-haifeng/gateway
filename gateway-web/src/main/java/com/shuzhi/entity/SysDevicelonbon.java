package com.shuzhi.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName SysDevicelonbon
 * @Author DiaoxiuChong@shuzhi.com
 * @Date 2019/6/27 14:49
 * @Version 1.0
 * @Description: 来邦设备配置
 **/
@Data
@Entity
@Table(name = "t_device_lonbon")
public class SysDevicelonbon {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue
    private Integer id;

    /***
     * 厂商ID
     */
    private Integer factory_id;

    /***
     * 设备类型
     */
    private Integer device_type;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 设备名称
     */
    private String device_name;


    /**
     * 设备方应用设备ID
     */
    private String device_id;

    /**
     * 平台设备ID
     */
    private String did;

    /**
     * 描述
     */
    private String describe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFactory_id() {
        return factory_id;
    }

    public void setFactory_id(Integer factory_id) {
        this.factory_id = factory_id;
    }

    public Integer getDevice_type() {
        return device_type;
    }

    public void setDevice_type(Integer device_type) {
        this.device_type = device_type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
