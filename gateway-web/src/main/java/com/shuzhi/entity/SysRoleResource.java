package com.shuzhi.entity;


import lombok.Data;

import javax.persistence.*;

/**
 * 角色资源关系
 * author: Yujq
 * date: 2018/4/5
 */
@Data
@Entity
@Table(name = "t_sysroleresource")
public class SysRoleResource {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 资源id
     */
    @Column(name = "resource_id")
    private Integer resourceId;
}
