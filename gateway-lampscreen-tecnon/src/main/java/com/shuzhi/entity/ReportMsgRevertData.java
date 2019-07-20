package com.shuzhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 上报消息返回实体
 * @Author: lirb
 * @CreateDate: 2019/7/15 17:31
 * @Version: 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportMsgRevertData {

    /**
     * 设备类型
     */
    private int type;
    /**
     * 厂商类型
     */
    private int subtype;
    /**
     * 平台设备编号
     */
    private String did;
    /**
     * 上报消息id，暂未定义
     */
    private String infoid;
    /**
     * 数据
     */
    private Object data;

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"subtype\":")
                .append(subtype);
        sb.append(",\"did\":")
                .append(did);
        sb.append(",\"infoid\":")
                .append(infoid);
        sb.append(",\"data\":")
                .append(data);
        sb.append('}');
        return sb.toString();

    }
}
