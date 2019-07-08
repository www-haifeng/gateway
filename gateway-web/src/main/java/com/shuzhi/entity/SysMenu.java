package com.shuzhi.entity;

import lombok.Data;


import javax.persistence.*;
import java.util.List;

/**
 * @ProjectName: gateway-parent
 * @Package: com.shuzhi.entity
 * @ClassName: SysMenu
 * @Author: 陈鑫晖
 * @Date: 2019/7/7 15:40
 */
@Data
@Entity
@Table(name = "t_sysmenu")
public class SysMenu {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 目录名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 父id
     */
    @Column(name = "pid")
    private Integer pid;

    /**
     * 访问路径
     */
    @Column(name = "url")
    private String url;

   /*
    * 子菜单列表
    */
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "pid")    //根据父级菜单ID，实现自关联（内部其实也就是一对多）
    private List<SysMenu> childList;

}
