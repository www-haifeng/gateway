package com.shuzhi.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Description: 照明设备表
 * @Author:     lirb
 * @CreateDate:   2019/8/12 9:32
 * @Version:   1.0
 **/
@Entity
@Table(name = "t_device_iotcomm", schema = "public", catalog = "gateways")
public class TDeviceIotcommEntity {
    /**
     * 主键序列
     */
    private int id;
    /**
     * t_device_factory主键
     */
    private Integer factoryId;
    /**
     * 集中器主键标识
     */
    private Integer cuuid;
    /**
     * 集中器代码标识 UID
     */
    private String ccuuId;
    /**
     * 集中器名称
     */
    private String ccuuName;
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
    @Column(name = "cuuid", nullable = true)
    public Integer getCuuid() {
        return cuuid;
    }

    public void setCuuid(Integer cuuid) {
        this.cuuid = cuuid;
    }

    @Basic
    @Column(name = "ccuu_id", nullable = true ,length = 128)
    public String getCcuuId() {
        return ccuuId;
    }

    public void setCcuuId(String ccuuId) {
        this.ccuuId = ccuuId;
    }

    @Basic
    @Column(name = "ccuu_name", nullable = false ,length = 255)
    public String getCcuuName() {
        return ccuuName;
    }

    public void setCcuuName(String ccuuName) {
        this.ccuuName = ccuuName;
    }

    @Basic
    @Column(name = "describe", nullable = false ,length = 255)
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
