package com.shuzhi.entity.command;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TimingSwitchScreenTask {
    private String _id = "591d519f5e3f190f697aaf18";
    //协议参数
    private String name = "7-0.58";
    private String _company = "alahover";
    private String _department = "539eaaedb6e1232a1566d9c2";
    private String _role = "539eaaedb6e1232a1566d9c3";
    private String _user = "check";
    private int __v = 0;
    private List<Object> schedules;
    private String dateCreated = getNowTime();
    private String createDate = getNowTime();
    private String createBy = "check";
    private String id = "5ba098d0502da61b67899c09";
    private String lng = "zh-CN";


    private static String getNowTime(){
        return (new SimpleDateFormat("yyyy-MM-dd").format(new Date()))+"T"+(new SimpleDateFormat("HH:mm:ss:SSS").format(new Date())+"Z");
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_company() {
        return _company;
    }

    public void set_company(String _company) {
        this._company = _company;
    }

    public String get_department() {
        return _department;
    }

    public void set_department(String _department) {
        this._department = _department;
    }

    public String get_role() {
        return _role;
    }

    public void set_role(String _role) {
        this._role = _role;
    }

    public String get_user() {
        return _user;
    }

    public void set_user(String _user) {
        this._user = _user;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public List<Object> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Object> schedules) {
        this.schedules = schedules;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"_id\":\"")
                .append(_id).append('\"');
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"_company\":\"")
                .append(_company).append('\"');
        sb.append(",\"_department\":\"")
                .append(_department).append('\"');
        sb.append(",\"_role\":\"")
                .append(_role).append('\"');
        sb.append(",\"_user\":\"")
                .append(_user).append('\"');
        sb.append(",\"__v\":")
                .append(__v);
        sb.append(",\"schedules\":")
                .append(schedules);
        sb.append(",\"dateCreated\":\"")
                .append(dateCreated).append('\"');
        sb.append(",\"createDate\":\"")
                .append(createDate).append('\"');
        sb.append(",\"createBy\":\"")
                .append(createBy).append('\"');
        sb.append(",\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"lng\":\"")
                .append(lng).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
