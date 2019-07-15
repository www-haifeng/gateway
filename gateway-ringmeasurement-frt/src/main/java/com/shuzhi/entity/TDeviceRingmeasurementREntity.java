package com.shuzhi.entity;

import javax.persistence.*;

/**
 * 环测设备实体类
 */
@Entity
@Table(name = "t_device_ringgmeasurement", schema = "public", catalog = "gateways")
public class TDeviceRingmeasurementREntity {

    /**
     * id
     */
    private int id;
    /**
     * 厂商id
     */
    private Integer factoryId;
    /**
     * 设备类型
     */
    private String deviceType;
    /**
     * 设备ip
     */
    private String ip;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 厂商设备id
     */
    private String deviceId;
    /**
     * 平台设备id
     */
    private String did;
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
    @Column(name = "factory_id", nullable = true)
    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    @Basic
    @Column(name = "device_type", nullable = true)
    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @Basic
    @Column(name = "ip", nullable = true)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "device_name", nullable = true)
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Basic
    @Column(name = "device_id", nullable = false)
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Basic
    @Column(name = "did", nullable = false)
    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    @Basic
    @Column(name = "describe", nullable = true)
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "TDeviceRingmeasurementREntity{" +
                "id=" + id +
                ", factoryId=" + factoryId +
                ", deviceType='" + deviceType + '\'' +
                ", ip='" + ip + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", did='" + did + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }
}
