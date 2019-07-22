package com.shuzhi.entity.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Description: 路人库检索 参数 实体
 * @Author:     lirb
 * @CreateDate:   2019/7/22 17:29
 * @Version:   1.0
 **/
@Data
public class PasserDbSearchData {

    @JsonProperty("pagerows")
    private Integer pageRows;

    @JsonProperty("pageno")
    private Integer pageNo;

    @JsonProperty("pageflag")
    private String pageFlag;

    @JsonProperty("starttime")
    private String partnerId;

    @JsonProperty("searchcondition")
    private PasserDbSearchConditonData searchCondition;
}
