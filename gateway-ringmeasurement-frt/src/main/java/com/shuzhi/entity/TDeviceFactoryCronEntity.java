package com.shuzhi.entity;

import javax.persistence.*;

/**
 * @Description: 设备厂商的定时任务实体类
 * @Author: lirb
 * @CreateDate: 2019/7/14 13:42
 * @Version: 1.0
 **/
@Entity
@Table(name = "t_device_factory_cron", schema = "public", catalog = "gateways")
public class TDeviceFactoryCronEntity {

    /**
     * id
     */
    private int id;

    /**
     * 厂商id
     */
    private Integer factoryId;
    /**
     * 厂商名称
     */
    private String factroyName;
    /**
     * 定时任务表达式
     */
    private String cron;
    /**
     * 描述
     */
    private String describe;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "factory_id", nullable = false)
    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    @Basic
    @Column(name = "factory_name", nullable = true)
    public String getFactroyName() {
        return factroyName;
    }

    public void setFactroyName(String factroyName) {
        this.factroyName = factroyName;
    }

    @Basic
    @Column(name = "cron", nullable = false)
    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    @Basic
    @Column(name = "describe", nullable = true)
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
