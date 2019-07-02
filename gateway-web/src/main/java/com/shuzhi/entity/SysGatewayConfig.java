package com.shuzhi.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName SysGatewayConfig
 * @Author DiaoxiuChong@shuzhi.com
 * @Date 2019/6/27 9:45
 * @Version 1.0
 * @Description: 网关服务配置列表
 **/
@Data
@Entity
@Table(name = "t_gateway_config")
public class SysGatewayConfig {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue
    private Integer id;

    /***
     * 系统id
     */

    private String sys_id;

    /***
     * 链路id
     */
    private String connect_id;

    /**
     * 链路类型
     */
    private Integer type_group_code;

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
     * socket 名字
     */
    private String socket_name;

    /**
     * 系统类型
     */
    private String sys_type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSys_id() {
        return sys_id;
    }

    public void setSys_id(String sys_id) {
        this.sys_id = sys_id;
    }

    public String getConnect_id() {
        return connect_id;
    }

    public void setConnect_id(String connect_id) {
        this.connect_id = connect_id;
    }

    public Integer getType_group_code() {
        return type_group_code;
    }

    public void setType_group_code(Integer type_group_code) {
        this.type_group_code = type_group_code;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getSocket_name() {
        return socket_name;
    }

    public void setSocket_name(String socket_name) {
        this.socket_name = socket_name;
    }

    public String getSys_type() {
        return sys_type;
    }

    public void setSys_type(String sys_type) {
        this.sys_type = sys_type;
    }
}
