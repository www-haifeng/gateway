package com.shuzhi.entity.commandResult;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskResult {
    @JsonProperty("_type")
    private String type;
    private String task;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"task\":\"")
                .append(task).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
