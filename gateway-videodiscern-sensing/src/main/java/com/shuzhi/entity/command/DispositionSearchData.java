package com.shuzhi.entity.command;

import lombok.Data;

/**
 * @Description: 查询布控任务参数实体
 * @Author:     lirb
 * @CreateDate:   2019/7/22 16:26
 * @Version:   1.0
 **/
@Data
public class DispositionSearchData {

    /**
     * 布控任务名称
     */
    private String name ;
    /**
     * 布控任务的id
     */
    private String uuid;

    /**
     * 用户标识
     */
    private String partnerId;

}
