package com.shuzhi.entity;

public class WiFiStyletInfo {
    private String stamac;
    private String apmac;
    private double xaxle;
    private double yaxle;
    private double zaxle;
    private String mapid;

    public WiFiStyletInfo(String stamac, String apmac, double xaxle, double yaxle, double zaxle, String mapid) {
        this.stamac = stamac;
        this.apmac = apmac;
        this.xaxle = xaxle;
        this.yaxle = yaxle;
        this.zaxle = zaxle;
        this.mapid = mapid;
    }

    public String getStamac() {
        return stamac;
    }

    public void setStamac(String stamac) {
        this.stamac = stamac;
    }

    public String getApmac() {
        return apmac;
    }

    public void setApmac(String apmac) {
        this.apmac = apmac;
    }

    public double getXaxle() {
        return xaxle;
    }

    public void setXaxle(double xaxle) {
        this.xaxle = xaxle;
    }

    public double getYaxle() {
        return yaxle;
    }

    public void setYaxle(double yaxle) {
        this.yaxle = yaxle;
    }

    public double getZaxle() {
        return zaxle;
    }

    public void setZaxle(double zaxle) {
        this.zaxle = zaxle;
    }

    public String getMapid() {
        return mapid;
    }

    public void setMapid(String mapid) {
        this.mapid = mapid;
    }
}
