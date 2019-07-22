package com.shuzhi.entity.command;

import lombok.Data;

/**
 * @Description: 查询目标人请求参数实体
 * @Author: lirb
 * @CreateDate: 2019/7/22 16:52
 * @Version: 1.0
 **/
@Data
public class FaceTemplateSearchData {

    /**
     * 目标人id
     */
    private String uuid;
    /**
     * 目标人名称
     */
    private String name;
    /**
     * 标识用户的身份
     */
    private String partnerId;
}
