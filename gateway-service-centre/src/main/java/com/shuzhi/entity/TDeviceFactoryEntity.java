package com.shuzhi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
* @Program:  网关服务配置表
* @Description:
* @Author: YuJQ
* @Create: 2019/6/4 13:15
**/
@Data
@Entity
@Table(name = "t_device_factory")
public class TDeviceFactoryEntity {
    @Id
    private int id;

    @Column(name = "type")
    private int type;

    @Column(name = "subtype")
    private int subtype;

    @Column(name = "type_name")
    private String typeName;

    @Column(name = "factory_name")
    private String factoryName;

    @Column(name = "server_ip")
    private String serverIp;

    @Column(name = "server_port")
    private int  serverPort;

    @Column(name = "describe")
    private String describe;

    @Column(name = "mq_type")
    private String mqType;

    @Column(name = "mq_subtype")
    private String mqSubtype;




}
