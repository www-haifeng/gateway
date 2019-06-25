package com.shuzhi.entity.command;

import com.sun.deploy.net.URLEncoder;

import java.io.UnsupportedEncodingException;

public class AddTaskInfo {
    private String taskname;
    private int isdisable;
    private int level;
    private String creator = "admin";
    private String triggers;
    private String commands;
    private String dirname;

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public int getIsdisable() {
        return isdisable;
    }

    public void setIsdisable(int isdisable) {
        this.isdisable = isdisable;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
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

    public String getDirname() {
        return dirname;
    }

    public void setDirname(String dirname) {
        this.dirname = dirname;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        try {
            sb.append("jsondata%5Btaskname%5D=")
                    .append(URLEncoder.encode((taskname), "utf-8"));
            sb.append("&jsondata%5Bisdisable%5D=")
                    .append(isdisable);
            sb.append("&jsondata%5Blevel%5D=")
                    .append(level);
            sb.append("&jsondata%5Bcreator%5D=")
                    .append(URLEncoder.encode((creator), "utf-8"));
            sb.append("&jsondata%5Btriggers%5D=")
                    .append(URLEncoder.encode((triggers), "utf-8"));
            sb.append("&jsondata%5Bcommands%5D=")
                    .append(URLEncoder.encode((commands), "utf-8"));
            sb.append("&jsondata%5Bdirname%5D=")
                    .append(URLEncoder.encode((dirname), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
/*  @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        try {
            sb.append("jsondata%5Btaskname%5D=")
                    .append(URLEncoder.encode((taskname), "utf-8"));
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
            sb.append("&jsondata%5Bdirname%5D=")
                    .append(dirname);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }*/
}
