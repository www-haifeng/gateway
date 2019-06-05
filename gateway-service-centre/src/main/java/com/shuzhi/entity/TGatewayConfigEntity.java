package com.shuzhi.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

/**
* @Program:  网关服务配置表
* @Description:
* @Author: YuJQ
* @Create: 2019/6/4 13:15
**/
@Data
@Entity
@Table(name = "t_gateway_config")
public class TGatewayConfigEntity {
    @Id
    private int id;

    @Column(name = "ip")
    private int ip;

    @Column(name = "port")
    private String port;

    @Column(name = "socket_name")
    private String socketName;

    @Column(name = "sys_id")
    private String sysId;

    @Column(name = "connect_id")
    private String connectId;

    @Column(name = "type_group_code")
    private String typeGroupCode;

    @Column(name = "describe")
    private String describe;


}
