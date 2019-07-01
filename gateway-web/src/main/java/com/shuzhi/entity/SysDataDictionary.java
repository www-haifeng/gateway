package com.shuzhi.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName SysDataDictionary
 * @Author DiaoxiuChong@shuzhi.com
 * @Date 2019/6/27 15:48
 * @Version 1.0
 * @Description: 数据字典管理
 **/
@Data
@Entity
@Table(name = "t_data_dictionary")
public class SysDataDictionary {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue
    private Integer id;

    /***
     * 字典编码
     */
    private String type_code;

    /**
     * 字典名称
     */
    private String type_name;

    /**
     * 字典组ID
     */
    private Integer type_group_id;

    /**
     * 描述
     */
    private String describe;

}
