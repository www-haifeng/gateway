package com.shuzhi.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_gateway_config", schema = "public", catalog = "gateways")
public class TGatewayConfigEntity {

    private int id;
    /**
     * 系统id
     */
    private String sysId;
    /**
     * 链路id
     */
    private String connectId;
    /**
     * 链路类型
     */
    private String typeGroupCode;
    /**
     * 描述
     */
    private String describe;
    /**
     * ip地址
     */
    private String ip;
    /**
     * 端口号
     */
    private Integer port;
    /**
     * socket名字
     */
    private String socketName;
    /**
     * 系统类型
     */
    private String sysType;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sys_id", nullable = true)
    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    @Basic
    @Column(name = "connect_id", nullable = true)
    public String getConnectId() {
        return connectId;
    }

    public void setConnectId(String connectId) {
        this.connectId = connectId;
    }

    @Basic
    @Column(name = "type_group_code", nullable = true)
    public String getTypeGroupCode() {
        return typeGroupCode;
    }

    public void setTypeGroupCode(String typeGroupCode) {
        this.typeGroupCode = typeGroupCode;
    }

    @Basic
    @Column(name = "describe", nullable = true)
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
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
    @Column(name = "port", nullable = true)
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Basic
    @Column(name = "socket_name", nullable = true)
    public String getSocketName() {
        return socketName;
    }

    public void setSocketName(String socketName) {
        this.socketName = socketName;
    }

    @Basic
    @Column(name = "sys_type", nullable = true)
    public String getSysType() {
        return sysType;
    }

    public void setSysType(String sysType) {
        this.sysType = sysType;
    }


}
