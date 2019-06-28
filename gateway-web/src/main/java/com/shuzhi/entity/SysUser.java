package com.shuzhi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户
 * author: Yujq
 * date: 2018/4/5
 */
@Data
@Entity
@Table(name = "t_sysuser")
public class SysUser {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 盐
     */
    @JsonIgnore
    private String salt;

    /**
     * 是否禁用 0：否；1：是
     */
    private Integer forbidden;
    /**
     * 是否有修改 0：没有；1：有
     */
    private Integer authority;
}
