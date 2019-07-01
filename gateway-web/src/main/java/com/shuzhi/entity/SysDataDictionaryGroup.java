package com.shuzhi.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @ClassName SysDataDictionaryGroup
 * @Author DiaoxiuChong@shuzhi.com
 * @Date 2019/6/27 15:43
 * @Version 1.0
 * @Description: 数据字典组管理
 **/
@Data
@Entity
@Table(name = "t_data_dictionary_group")
public class SysDataDictionaryGroup {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue
    private Integer id;

    /***
     * 字典组编码
     */
    private String type_group_code;

    /**
     * 字典组名称
     */
    private String type_group_name;

    /**
     * 字典信息
     */
    @OneToMany//一对多
    @JoinColumn(name="type_group_id")//关联的外键列
    private List<SysDataDictionary> SysDataDictionary;

}
