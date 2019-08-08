package com.shuzhi.entity.report;

/**
 * @Description: 上报消息回执二层数据
 * @Author:     lirb
 * @CreateDate:   2019/7/31 11:14
 * @Version:   1.0
 **/
public class MessageReportData {
    private int type;
    private int subtype;
    private String did;
    private String infoid;
    private Object data;

    public MessageReportData(){

    }

    public MessageReportData(Object data){
        this.data=data;
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

    public String getInfoid() {
        return infoid;
    }

    public void setInfoid(String infoid) {
        this.infoid = infoid;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
