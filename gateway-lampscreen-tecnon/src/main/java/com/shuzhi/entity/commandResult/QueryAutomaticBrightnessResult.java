package com.shuzhi.entity.commandResult;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryAutomaticBrightnessResult {
    @JsonProperty("_type")
    private String type;

    private String sensitivity;

    @JsonProperty("minBrightness")
    private String minbrightness;

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

    public String getMinbrightness() {
        return minbrightness;
    }

    public void setMinbrightness(String minbrightness) {
        this.minbrightness = minbrightness;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"sensitivity\":\"")
                .append(sensitivity).append('\"');
        sb.append(",\"minbrightness\":\"")
                .append(minbrightness).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
