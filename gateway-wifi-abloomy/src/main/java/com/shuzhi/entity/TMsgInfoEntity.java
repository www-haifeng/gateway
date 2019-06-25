package com.shuzhi.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_msg_info", schema = "public", catalog = "gateways")
public class TMsgInfoEntity {
    private int id;
    private Integer factoryId;
    private Integer msgType;
    private String msgId;
    private String interfaceId;
    private String requestType;
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
    @Column(name = "msg_type", nullable = true)
    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    @Basic
    @Column(name = "msg_id", nullable = true, length = 255)
    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @Basic
    @Column(name = "interface_id", nullable = true, length = 255)
    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    @Basic
    @Column(name = "request_type", nullable = true, length = 255)
    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
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
        TMsgInfoEntity that = (TMsgInfoEntity) o;
        return id == that.id &&
                Objects.equals(factoryId, that.factoryId) &&
                Objects.equals(msgType, that.msgType) &&
                Objects.equals(msgId, that.msgId) &&
                Objects.equals(interfaceId, that.interfaceId) &&
                Objects.equals(requestType, that.requestType) &&
                Objects.equals(describe, that.describe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, factoryId, msgType, msgId, interfaceId, requestType, describe);
    }
}
