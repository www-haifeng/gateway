package com.shuzhi.entity.command;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class GetMediaData {
    @JsonProperty("pageindex")
    private Integer pageIndex;

    @JsonProperty("pagecount")
    private Integer pageCount;

    @JsonProperty("subpath")
    private String subPath;

    private String filter;

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

    public String getSubPath() {
        return subPath;
    }

    public void setSubPath(String subPath) {
        this.subPath = subPath;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("jsondata%5BpageIndex%5D=")
                .append(pageIndex);
        sb.append("&jsondata%5BpageCount%5D=")
                .append(pageCount);
        try {
            sb.append("&jsondata%5BsubPath%5D=")
                    .append(URLEncoder.encode((subPath), "utf-8"));
            sb.append("&jsondata%5Bfilter%5D=")
                    .append(URLEncoder.encode((filter), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /*@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("jsondata%5BpageIndex%5D=")
                .append(pageIndex);
        sb.append("&jsondata%5BpageCount%5D=")
                .append(pageCount);
        sb.append("&jsondata%5BsubPath%5D=")
                .append(subPath);
        sb.append("&jsondata%5Bfilter%5D=")
                .append(filter);
        return sb.toString();
    }*/

}
