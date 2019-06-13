package com.shuzhi.entity.command;

/**
 * @Description:    定时亮度参数
 * @Author: YHF
 * @date 2019/6/12
 */
public class TimingBrightnessParameters {
    private int defaultbrightness;
    private int brightness;
    private String startdate;
    private String enddate;
    private String starttime;
    private String endtime;

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
}
