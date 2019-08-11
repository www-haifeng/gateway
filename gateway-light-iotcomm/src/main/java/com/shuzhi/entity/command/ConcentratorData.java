package com.shuzhi.entity.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description: 集中器返回的数据封装
 * @Author:     lirb
 * @CreateDate:   2019/8/11 10:43
 * @Version:   1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConcentratorData {

    /**
     * 提示消息
     */
    private String message;
    private String pagination;
    private String status;
    private String success;
    private Integer total;
    private String value;
    private Object  rows;
}
