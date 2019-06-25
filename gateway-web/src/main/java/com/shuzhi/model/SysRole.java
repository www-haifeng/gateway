package com.shuzhi.model;

import lombok.Data;

import javax.persistence.*;

/**
 * 角色
 * author: Yujq
 * date: 2018/4/5
 */
@Data
@Entity
@Table(name = "t_sysrole")
public class SysRole {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色等级
     */
    private Integer grade;

    /**
     * 备注
     */
    private String remark;
}
