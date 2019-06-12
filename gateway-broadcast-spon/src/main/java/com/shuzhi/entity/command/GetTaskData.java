package com.shuzhi.entity.command;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetTaskData {
    @JsonProperty("pageindex")
    private int pageIndex;

    @JsonProperty("pagecount")
    private int pageCount;

    private String dir;

    private String user = "admin";

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("jsondata%5BpageIndex%5D=")
                .append(pageIndex);
        sb.append("&jsondata%5BpageCount%5D=")
                .append(pageCount);
        sb.append("&jsondata%5Bdir%5D=")
                .append(dir);
        sb.append("&jsondata%5Buser%5D=")
                .append(user);
        return sb.toString();
    }
}
