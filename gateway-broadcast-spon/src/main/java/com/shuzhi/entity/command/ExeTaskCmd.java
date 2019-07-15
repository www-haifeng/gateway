package com.shuzhi.entity.command;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ExeTaskCmd {
    @JsonProperty("taskid")
    private String taskId;
    @JsonProperty("taskcommand")
    private String taskCommand;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskCommand() {
        return taskCommand;
    }

    public void setTaskCommand(String taskCommand) {
        this.taskCommand = taskCommand;
    }

    /*@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("jsondata%5BtaskId%5D=")
                .append(taskId);
        sb.append("&jsondata%5BtaskCommand%5D=")
                .append(taskCommand);
        return sb.toString();
    }*/

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("jsondata%5BtaskId%5D=")
                .append(taskId);
        sb.append("&jsondata%5BtaskCommand%5D=")
                .append(taskCommand);
        return sb.toString();
    }
}
