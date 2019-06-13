package com.shuzhi.entity.command;

public class TimingBrightness {
    private String type = "timedBrightness";
    private TimingBrightnessTask task;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TimingBrightnessTask getTask() {
        return task;
    }

    public void setTask(TimingBrightnessTask task) {
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
