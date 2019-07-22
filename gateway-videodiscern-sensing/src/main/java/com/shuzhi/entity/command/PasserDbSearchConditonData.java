package com.shuzhi.entity.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
/**
 * @Description: 路人库检索 参数条件 实体
 * @Author:     lirb
 * @CreateDate:   2019/7/22 17:31
 * @Version:   1.0
 **/
@Data
public class PasserDbSearchConditonData {

    /**
     * 起始时间
     */
    @JsonProperty("starttime")
    private String startTime;

    /**
     * 结束时间
     */
    @JsonProperty("endtime")
    private String endTime;

    /**
     * 通道Id组合，以 ";" 分割
     */
    @JsonProperty("childlist")
    private String childList;

}
