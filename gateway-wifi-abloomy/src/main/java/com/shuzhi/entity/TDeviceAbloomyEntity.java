package com.shuzhi.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_device_abloomy", schema = "public", catalog = "gateways")
public class TDeviceAbloomyEntity {
    private int id;
    private Integer factoryId;
    private String mac;
    private String ip;
    private String deviceName;
    private String deviceId;
    private String did;
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
    @Column(name = "mac", nullable = true, length = 255)
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Basic
    @Column(name = "ip", nullable = true, length = 255)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "device_name", nullable = true, length = 255)
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Basic
    @Column(name = "device_id", nullable = true, length = 255)
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Basic
    @Column(name = "did", nullable = true, length = 255)
    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    @Basic
    @Column(name = "describe", nullable = true, length = 255)
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TDeviceAbloomyEntity that = (TDeviceAbloomyEntity) o;
        return id == that.id &&
                Objects.equals(factoryId, that.factoryId) &&
                Objects.equals(mac, that.mac) &&
                Objects.equals(ip, that.ip) &&
                Objects.equals(deviceName, that.deviceName) &&
                Objects.equals(deviceId, that.deviceId) &&
                Objects.equals(did, that.did) &&
                Objects.equals(describe, that.describe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, factoryId, mac, ip, deviceName, deviceId, did, describe);
    }
}
