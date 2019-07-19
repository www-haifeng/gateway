package com.shuzhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 设备返回数据实体类封装
 */
@Data
@AllArgsConstructor
public class DataEntity {


    /**
     * 平台设备id
     */
    private  String did;

    /**
     * 环温
     */
    private  double ringTemperature;
    /**
     * 环湿
     */
    private  double ringWetting;
    /**
     * 气压
     */
    private  double pressure;
    /**
     * 风向
     */
    private  int windDirection;
    /**
     * 风速
     */
    private  double windSpeed;
    /**
     * 雨量
     */
    private  double rainfall;
    /**
     * PM2.5
     */
    private  double pmTwoPointFive;
    /**
     * PM10
     */
    private  double pmTen;
    /**
     * 噪声
     */
    private  double noise;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"did\":\"")
                .append(did).append('\"');
        sb.append(",\"ringTemperature\":")
                .append(ringTemperature);
        sb.append(",\"ringWetting\":")
                .append(ringWetting);
        sb.append(",\"pressure\":")
                .append(pressure);
        sb.append(",\"windDirection\":")
                .append(windDirection);
        sb.append(",\"windSpeed\":\"")
                .append(windSpeed).append('\"');
        sb.append(",\"pmTwoPointFive\":\"")
                .append(pmTwoPointFive).append('\"');
        sb.append(",\"pmTen\":\"")
                .append(pmTen).append('\"');
        sb.append(",\"noise\":")
                .append(noise);
        sb.append('}');
        return sb.toString();
    }
}
