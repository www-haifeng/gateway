package com.shuzhi.entity.commandResult;

/**
 * @Description: 查询定时开关屏返回格式
 * @Author: YHF
 * @date 2019/6/12
 */
public class QueryTimingBrightnessResult {
    private String type;
    private int defaultbrightness;
    private int brightness;
    private int nowbrightness;
    private String startdate;
    private String enddate;
    private String starttime;
    private String endtime;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDefaultbrightness() {
        return defaultbrightness;
    }

    public void setDefaultbrightness(int defaultbrightness) {
        this.defaultbrightness = defaultbrightness;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public int getNowbrightness() {
        return nowbrightness;
    }

    public void setNowbrightness(int nowbrightness) {
        this.nowbrightness = nowbrightness;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"defaultbrightness\":")
                .append(defaultbrightness);
        sb.append(",\"brightness\":")
                .append(brightness);
        sb.append(",\"nowbrightness\":")
                .append(nowbrightness);
        sb.append(",\"startdate\":\"")
                .append(startdate).append('\"');
        sb.append(",\"enddate\":\"")
                .append(enddate).append('\"');
        sb.append(",\"starttime\":\"")
                .append(starttime).append('\"');
        sb.append(",\"endtime\":\"")
                .append(endtime).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
