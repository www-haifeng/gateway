package com.shuzhi.entity.commandResult;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetScreenShotsResult {
    @JsonProperty("_type")
    private String type;
    private String result;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"result\":\"")
                .append(result).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
