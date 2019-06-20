package com.shuzhi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by ztt on 2019/6/19
 **/
@Data //生成getter,setter等函数
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor//生成无参构造函数
@Builder
public class ResultDataEntity {
    private int result;
    private List<Terminal> terminalinfos;
    private int[] hostnums;
    private int[] ternums;
    private String errinfo;

}
