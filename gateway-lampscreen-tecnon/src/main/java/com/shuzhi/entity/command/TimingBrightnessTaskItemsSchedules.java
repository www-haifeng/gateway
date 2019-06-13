package com.shuzhi.entity.command;

import java.util.List;

public class TimingBrightnessTaskItemsSchedules {
    private String dateType = "All";
    //协议参数
    private String startDate;
    //协议参数
    private String endDate;
    private String timeType = "Range";
    //协议参数
    private String startTime;
    //协议参数
    private String endTime;
    private String filterType = "None";
    private List weekFilter;
    private List monthFilter;
    private String lng = "en";

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public List getWeekFilter() {
        return weekFilter;
    }

    public void setWeekFilter(List weekFilter) {
        this.weekFilter = weekFilter;
    }

    public List getMonthFilter() {
        return monthFilter;
    }

    public void setMonthFilter(List monthFilter) {
        this.monthFilter = monthFilter;
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
        sb.append("\"dateType\":\"")
                .append(dateType).append('\"');
        sb.append(",\"startDate\":\"")
                .append(startDate).append('\"');
        sb.append(",\"endDate\":\"")
                .append(endDate).append('\"');
        sb.append(",\"timeType\":\"")
                .append(timeType).append('\"');
        sb.append(",\"startTime\":\"")
                .append(startTime).append('\"');
        sb.append(",\"endTime\":\"")
                .append(endTime).append('\"');
        sb.append(",\"filterType\":\"")
                .append(filterType).append('\"');
        sb.append(",\"weekFilter\":")
                .append(weekFilter);
        sb.append(",\"monthFilter\":")
                .append(monthFilter);
        sb.append(",\"lng\":\"")
                .append(lng).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
