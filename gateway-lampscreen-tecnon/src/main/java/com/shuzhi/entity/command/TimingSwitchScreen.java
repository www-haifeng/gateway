package com.shuzhi.entity.command;

public class TimingSwitchScreen {
    private String type = "timedScreening";
    private TimingSwitchScreenTask task;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TimingSwitchScreenTask getTask() {
        return task;
    }

    public void setTask(TimingSwitchScreenTask task) {
        this.task = task;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"task\":")
                .append(task);
        sb.append('}');
        return sb.toString();
    }
}
