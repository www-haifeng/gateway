package com.shuzhi.entity;

import lombok.Data;

/*
 * @Description: 封装一键告警设备在线状态
 * @Author: YHF
 * @date 2019/7/31
 */
@Data
public class DeviceState {
    //设备号
    private int ternum;
    //在线状态 0离线  1在线
    private int state;
    //设备类型 0主机  1分机
    private int type;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"ternum\":")
                .append(ternum);
        sb.append(",\"state\":")
                .append(state);
        sb.append(",\"type\":")
                .append(type);
        sb.append('}');
        return sb.toString();
    }
}
