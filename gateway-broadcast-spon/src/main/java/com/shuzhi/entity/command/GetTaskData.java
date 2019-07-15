package com.shuzhi.entity.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shuzhi.commen.Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class GetTaskData {
    @JsonProperty("pageindex")
    private Integer pageIndex;

    @JsonProperty("pagecount")
    private Integer pageCount;

    private String dir;

    private String user = "admin";

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
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
                .append(Utils.encodeUTF8(user));
        return sb.toString();
    }
/*@Override
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
    }*/
}
