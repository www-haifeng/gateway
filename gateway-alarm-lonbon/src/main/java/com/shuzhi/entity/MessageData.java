package com.shuzhi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //生成getter,setter等函数
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor//生成无参构造函数
@Builder
public class MessageData {
//    private int overtime;
    private int type;
    private int subtype;
    private String did;
    private String infoid;
    private String cmdid;
    private Object data;

}
