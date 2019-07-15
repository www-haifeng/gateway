package com.shuzhi.entity;

import com.alibaba.fastjson.JSONArray;

public class ReceiptJob {
    private String id;
    private String name;
    private String isDisable;
    private String createUser;
    private String taskLevel;
    private JSONArray triggers;
    private JSONArray commands;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(String isDisable) {
        this.isDisable = isDisable;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getTaskLevel() {
        return taskLevel;
    }

    public void setTaskLevel(String taskLevel) {
        this.taskLevel = taskLevel;
    }

    public JSONArray getTriggers() {
        return triggers;
    }

    public void setTriggers(JSONArray triggers) {
        this.triggers = triggers;
    }

    public JSONArray getCommands() {
        return commands;
    }

    public void setCommands(JSONArray commands) {
        this.commands = commands;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":\"")
                .append(id).append('\"');
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"isDisable\":\"")
                .append(isDisable).append('\"');
        sb.append(",\"createUser\":\"")
                .append(createUser).append('\"');
        sb.append(",\"taskLevel\":\"")
                .append(taskLevel).append('\"');
        sb.append(",\"triggers\":")
                .append(triggers);
        sb.append(",\"commands\":")
                .append(commands);
        sb.append('}');
        return sb.toString();
    }
}
