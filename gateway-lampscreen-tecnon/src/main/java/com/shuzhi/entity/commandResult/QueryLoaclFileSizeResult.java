package com.shuzhi.entity.commandResult;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryLoaclFileSizeResult {
    @JsonProperty("_type")
    private String type;
    private int length;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"length\":")
                .append(length);
        sb.append('}');
        return sb.toString();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
