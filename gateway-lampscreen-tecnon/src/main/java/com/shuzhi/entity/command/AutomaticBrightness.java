package com.shuzhi.entity.command;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AutomaticBrightness {
    private String type = "setAutoBrightness";
    private String sensitivity;
    @JsonProperty("minbrightness")
    private String minBrightness;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(String sensitivity) {
        this.sensitivity = sensitivity;
    }

    public String getMinBrightness() {
        return minBrightness;
    }

    public void setMinBrightness(String minBrightness) {
        this.minBrightness = minBrightness;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"sensitivity\":\"")
                .append(sensitivity).append('\"');
        sb.append(",\"minBrightness\":\"")
                .append(minBrightness).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
