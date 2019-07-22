package com.shuzhi.entity.command;

import lombok.Data;

/**
 * @Description: 修改布控任务状态参数实体
 * @Author:     lirb
 * @CreateDate:   2019/7/22 16:34
 * @Version:   1.0
 **/
@Data
public class DispositionUpdateData {

    /**
     * 布控任务id
     */
    private String uuid;
    /**
     * 布控任务的状态 0：关闭；1：开启
     */
    private String state;
    /**
     * 用户标识
     */
    private String partnerId;
}
