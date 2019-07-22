package com.shuzhi.entity.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
/**
 * @Description: 创建布控任务请求实体类
 * @Author:     lirb
 * @CreateDate:   2019/7/22 15:24
 * @Version:   1.0
 **/
@Data
public class DispositionCreateData {

    /**
     * 任务名称
     */
    private String name;

    /**
     * 布控任务描述
     */
    private String memo;

    /**
     * 布控任务的阈值
     */
    @JsonProperty("jobscore")
    private Integer jobScore;

    /**
     * 布控通道的id
     */
    @JsonProperty("jobchannels")
    private String jobChannels;

    /**
     * 布控的目标库id
     */
    @JsonProperty("jobtemplatedbs")
    private String jobTemplateDbs;

    /**
     * 用户自定义id
     */
    @JsonProperty("customerid")
    private String customerId;

    /**
     * 用户自定义data
     */
    @JsonProperty("customerdata")
    private String customerData;

    /**
     * 标识用户的身份
     */
    private String partnerId;

}
