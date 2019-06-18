package com.shuzhi.entity;

public class WiFiStyleData {
    private String wifi;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"wifi\":")
                .append(wifi);
        sb.append('}');
        return sb.toString();
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public WiFiStyleData(String wifi) {
        this.wifi = wifi;
    }
}
