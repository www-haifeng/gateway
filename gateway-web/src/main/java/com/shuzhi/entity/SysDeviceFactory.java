package com.shuzhi.entity;

import lombok.Data;

import javax.persistence.*;


/**
 * @ProjectName: gateway-parent
 * @Package: com.shuzhi.entity
 * @ClassName: SysDeciveFactory
 * @Author: 陈鑫晖
 * @Date: 2019/7/8 16:47
 */
@Data
@Entity
@Table(name = "t_device_factory")
public class SysDeviceFactory {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 设备类型
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 厂商类型
     */
    @Column(name = "subtype")
    private Integer subtype;

    /**
     * 设备类型名称
     */
    @Column(name = "type_name")
    private String typeName;

    /**
     * 厂商名称
     */
    @Column(name = "factory_name")
    private String factoryName;

    /**
     * 服务ip地址
     */
    @Column(name = "server_ip")
    private String serverIp;

    /**
     * 服务端口
     */
    @Column(name = "server_port")
    private Integer serverPort;

    /**
     * 描述
     */
    @Column(name = "describe")
    private String describe;

    /**
     * mq设备类型
     */
    @Column(name = "mq_type")
    private String mqType;

    /**
     * mq厂商类型
     */
    @Column(name = "mq_subtype")
    private String mqSubtype;



}
