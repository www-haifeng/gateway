package com.shuzhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 获取访问令牌返回结果集封装
 * @Author:     lirb
 * @CreateDate:   2019/8/12 15:53
 * @Version:   1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppLoginData {

    private String message;
    private String status;
    private Boolean success=false;
    private String value;
}
