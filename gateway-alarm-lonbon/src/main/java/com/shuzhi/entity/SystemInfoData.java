package com.shuzhi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //生成getter,setter等函数
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor//生成无参构造函数
@Builder
public class SystemInfoData {
    //报文消息id
    private String msgid;
    //消息类型
    private int msgtype;
    //系统类型
    private int systype;
    //系统id
    private int sysid;
    //链路id
    private int connectid;
    //校验
    private String sign;
    //时间戳
    private String msgts;
    //报文数据信息
    private Object msg;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"msgid\":\"")
                .append(msgid).append('\"');
        sb.append(",\"msgtype\":")
                .append(msgtype);
        sb.append(",\"systype\":")
                .append(systype);
        sb.append(",\"sysid\":")
                .append(sysid);
        sb.append(",\"connectid\":")
                .append(connectid);
        sb.append(",\"sign\":\"")
                .append(sign).append('\"');
        sb.append(",\"msgts\":\"")
                .append(msgts).append('\"');
        sb.append(",\"msg\":")
                .append(msg);
        sb.append('}');
        return sb.toString();
    }
}
