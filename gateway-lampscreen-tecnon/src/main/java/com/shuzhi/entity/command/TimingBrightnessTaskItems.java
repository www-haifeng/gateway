package com.shuzhi.entity.command;

import java.util.List;

public class TimingBrightnessTaskItems {
    //协议参数
    private int brightness;
    private String _id ="591d519f5e3f190f697aaf1a";
    private List schedules;
    private String id = "591d519f5e3f190f697aaf1a";

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List getSchedules() {
        return schedules;
    }

    public void setSchedules(List schedules) {
        this.schedules = schedules;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"brightness\":")
                .append(brightness);
        sb.append(",\"_id\":\"")
                .append(_id).append('\"');
        sb.append(",\"schedules\":")
                .append(schedules);
        sb.append(",\"id\":\"")
                .append(id).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
