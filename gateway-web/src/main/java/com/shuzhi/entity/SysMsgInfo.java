package com.shuzhi.entity;

import lombok.Data;

import javax.persistence.*;


/**
 * @ProjectName: gateway-parent
 * @Package: com.shuzhi.entity
 * @ClassName: SysMsgInfo
 * @Author: 陈鑫晖
 * @Date: 2019/7/8 16:47
 */
@Data
@Entity
@Table(name = "t_msg_info")
public class SysMsgInfo {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 厂商id
     */
    @Column(name = "factory_id")
    private Integer factoryId;

    /**
     * 厂商类型
     */
    @Column(name = "msg_type")
    private Integer msgType;

    /**
     * 消息id
     */
    @Column(name = "msg_id")
    private String  msgId;

    /**
     * 命令id
     */
    @Column(name = "interface_id")
    private String interfaceId;

    /**
     * 请求类型
     */
    @Column(name = "request_type")
    private String requestType;

    /**
     * 描述
     */
    @Column(name = "describe")
    private String describe;
}
