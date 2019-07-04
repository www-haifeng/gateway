package com.shuzhi.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_device_factory", schema = "public", catalog = "gateways")
public class TDeviceFactoryEntity {
    private int id;
    private Integer type;
    private Integer subtype;
    private String typeName;
    private String factoryName;
    private String serverIp;
    private Integer serverPort;
    private String describe;
    private String mqType;
    private String mqSubType;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "subtype", nullable = true)
    public Integer getSubtype() {
        return subtype;
    }

    public void setSubtype(Integer subtype) {
        this.subtype = subtype;
    }

    @Basic
    @Column(name = "type_name", nullable = true, length = 255)
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Basic
    @Column(name = "factory_name", nullable = true, length = 255)
    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    @Basic
    @Column(name = "server_ip", nullable = true, length = 255)
    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    @Basic
    @Column(name = "server_port", nullable = true)
    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    @Basic
    @Column(name = "describe", nullable = true, length = 255)
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Basic
    @Column(name = "mq_type", nullable = true, length = 255)
    public String getMqType() {
        return mqType;
    }

    public void setMqType(String mqType) {
        this.mqType = mqType;
    }

    @Basic
    @Column(name = "mq_subtype", nullable = true, length = 255)
    public String getMqSubType() {
        return mqSubType;
    }

    public void setMqSubType(String mqSubType) {
        this.mqSubType = mqSubType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TDeviceFactoryEntity that = (TDeviceFactoryEntity) o;
        return id == that.id &&
                Objects.equals(type, that.type) &&
                Objects.equals(subtype, that.subtype) &&
                Objects.equals(typeName, that.typeName) &&
                Objects.equals(factoryName, that.factoryName) &&
                Objects.equals(serverIp, that.serverIp) &&
                Objects.equals(serverPort, that.serverPort) &&
                Objects.equals(describe, that.describe)&&
                Objects.equals(mqType, that.mqType)&&
                Objects.equals(mqSubType, that.mqSubType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, subtype, typeName, factoryName, serverIp, serverPort, describe, mqType, mqSubType);
    }
}
