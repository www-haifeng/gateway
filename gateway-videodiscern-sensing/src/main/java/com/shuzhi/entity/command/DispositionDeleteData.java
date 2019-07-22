package com.shuzhi.entity.command;

import lombok.Data;

/**
 * @Description: 删除布控任务请求参数
 * @Author:     lirb
 * @CreateDate:   2019/7/22 16:31
 * @Version:   1.0
 **/
@Data
public class DispositionDeleteData {

    /**
     * 布控任务id
     */
    private String uuid;
    /**
     * 标识用户的身份
     */
    private String partnerId;
}
