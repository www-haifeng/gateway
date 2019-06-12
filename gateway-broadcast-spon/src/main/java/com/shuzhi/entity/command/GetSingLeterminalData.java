package com.shuzhi.entity.command;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * 功能描述 《应用网关-广播协议1.1 获取终端信息(多个)》
 * @author YHF
 * @date 2019/6/6
 * @params  
 * @return 
 */
public class GetSingLeterminalData {

    @JsonProperty("pageindex")
    private int pageIndex;

    @JsonProperty("pagecount")
    private int pageCount;

    @JsonProperty("groupname")
    private String groupName;


    @JsonProperty("showtype")

    private String showType;

    private String user = "admin";

    @JsonProperty("searchtxt")
    private String searchTxt;

    private int simple;

    public int getAgeIndex() {
        return pageIndex;
    }

    public void setAgeIndex(int ageIndex) {
        this.pageIndex = ageIndex;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getSimple() {
        return simple;
    }

    public void setSimple(int simple) {
        this.simple = simple;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getSearchTxt() {
        return searchTxt;
    }

    public void setSearchTxt(String searchTxt) {
        this.searchTxt = searchTxt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("jsondata%5BpageIndex%5D=")
                .append(pageIndex);
        sb.append("&jsondata%5BpageCount%5D=")
                .append(pageCount);
        sb.append("&jsondata%5BgroupName%5D=")
                .append(groupName);
        sb.append("&jsondata%5BshowType%5D=")
                .append(showType);
        sb.append("&jsondata%5Buser%5D=")
                .append(user);
        sb.append("&jsondata%5Bsimple%5D=")
                .append(simple);
        sb.append("&jsondata%5BsearchTxt%5D=")
                .append(searchTxt);
        return sb.toString();
    }
}
