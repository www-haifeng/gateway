package com.shuzhi.entity.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shuzhi.commen.Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/*
 * 功能描述 《应用网关-广播协议1.1 获取终端信息(多个)》
 * @author YHF
 * @date 2019/6/6
 * @params  
 * @return 
 */
public class GetSingLeterminalData {

    @JsonProperty("pageindex")
    private Integer pageIndex;

    @JsonProperty("pagecount")
    private Integer pageCount;

    @JsonProperty("groupname")
    private String groupName = "*";


    @JsonProperty("showtype")

    private String showType;

    private String user = "admin";

    @JsonProperty("searchtxt")
    private String searchTxt;

    private Integer simple;

    public Integer getAgeIndex() {
        return pageIndex;
    }

    public void setAgeIndex(Integer ageIndex) {
        this.pageIndex = ageIndex;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
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

    public Integer getSimple() {
        return simple;
    }

    public void setSimple(Integer simple) {
        this.simple = simple;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
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
                .append(Utils.encodeUTF8(showType));
        sb.append("&jsondata%5Buser%5D=")
                .append(Utils.encodeUTF8(user));
        sb.append("&jsondata%5BsearchTxt%5D=")
                .append(Utils.encodeUTF8(searchTxt));
        sb.append("&jsondata%5Bsimple%5D=")
                .append(simple);
        return sb.toString();
    }
/*@Override
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
    }*/
}
