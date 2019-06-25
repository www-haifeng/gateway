package com.shuzhi.model;

import lombok.Data;

import javax.persistence.*;

/**
 * 资源
 * author: Yujq
 * date: 2018/4/5
 */
@Data
@Entity
@Table(name = "t_sysresource")
public class SysResource {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 资源父id
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 权限标识符
     */
    @Column(name = "perms")
    private String perms;

    /**
     * 类型：0：目录，1：菜单，2：按钮
     */
    private String type;
}
