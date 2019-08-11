package com.shuzhi.entity;

/**
 * @Description: 接收信息的message层 =》第二层
 * @Author:     lirb
 * @CreateDate:   2019/8/9 11:40
 * @Version:   1.0
 **/

public class MessageData {
    private int overtime;
    private int type;
    private int subtype;
    private String did;
    private String cmdid;
    private Object data;

    public int getOvertime() {
        return overtime;
    }

    public void setOvertime(int overtime) {
        this.overtime = overtime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSubtype() {
        return subtype;
    }

    public void setSubtype(int subtype) {
        this.subtype = subtype;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getCmdid() {
        return cmdid;
    }

    public void setCmdid(String cmdid) {
        this.cmdid = cmdid;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
