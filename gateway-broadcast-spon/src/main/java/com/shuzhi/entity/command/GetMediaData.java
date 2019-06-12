package com.shuzhi.entity.command;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetMediaData {
    @JsonProperty("pageindex")
    private int pageIndex;

    @JsonProperty("pagecount")
    private int pageCount;

    @JsonProperty("subpath")
    private String subPath;

    private String filter;

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
        sb.append("&jsondata%5BsubPath%5D=")
                .append(subPath);
        sb.append("&jsondata%5Bfilter%5D=")
                .append(filter);
        return sb.toString();
    }
}
