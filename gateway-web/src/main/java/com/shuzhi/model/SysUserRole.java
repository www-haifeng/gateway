package com.shuzhi.model;

import lombok.Data;

import javax.persistence.*;

/**
 * 用户角色关系
 * author: Yujq
 * date: 2018/4/5
 */
@Data
@Entity
@Table(name = "t_sysuserrole")
public class SysUserRole {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue
    private Integer id;


    /**
     * 用户Id
     */
    @Column(name = "user_id")
    private Integer userId;


    /**
     * 角色Id
     */
    @Column(name = "role_id")
    private Integer roleId;
}
