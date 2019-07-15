package com.shuzhi.entity.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shuzhi.commen.Utils;
import com.shuzhi.util.StringUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ModifyTaskInfo {
    private String taskname;
    private Integer isdisable;
    private Integer level;
    private String creator = "admin";
    private String triggers;
    private String commands;
    @JsonProperty("taskid")
    private String taskId;

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public Integer getIsdisable() {
        return isdisable;
    }

    public void setIsdisable(Integer isdisable) {
        this.isdisable = isdisable;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getTriggers() {
        return triggers;
    }

    public void setTriggers(String triggers) {
        this.triggers = triggers;
    }

    public String getCommands() {
        return commands;
    }

    public void setCommands(String commands) {
        this.commands = commands;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("jsondata%5Btaskname%5D=")
                .append(Utils.encodeUTF8(taskname));
        sb.append("&jsondata%5Bisdisable%5D=")
                .append(isdisable);
        sb.append("&jsondata%5Blevel%5D=")
                .append(level);
        sb.append("&jsondata%5Bcreator%5D=")
                .append(Utils.encodeUTF8(creator));
        sb.append("&jsondata%5Btriggers%5D=")
                .append(Utils.encodeUTF8(triggers));
        sb.append("&jsondata%5Bcommands%5D=")
                .append(Utils.encodeUTF8(commands));
        sb.append("&jsondata%5BtaskId%5D=")
                .append(Utils.encodeUTF8(taskId));
        return sb.toString();
    }
/* @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("jsondata%5Btaskname%5D=")
                .append(taskname);
        sb.append("&jsondata%5Bisdisable%5D=")
                .append(isdisable);
        sb.append("&jsondata%5Blevel%5D=")
                .append(level);
        sb.append("&jsondata%5Bcreator%5D=")
                .append(creator);
        sb.append("&jsondata%5Btriggers%5D=")
                .append(triggers);
        sb.append("&jsondata%5Bcommands%5D=")
                .append(commands);
        sb.append("&jsondata%5BtaskId%5D=")
                .append(taskId);
        return sb.toString();
    }*/
}
