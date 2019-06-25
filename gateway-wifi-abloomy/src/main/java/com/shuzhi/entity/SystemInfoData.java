package com.shuzhi.entity;

public class SystemInfoData {
    //报文消息id
    private String msgid;
    //消息类型
    private int msgtype;
    //系统类型
    private int systype;
    //系统id
    private int sysid;
    //链路id
    private int connectid;
    //校验
    private String sign;
    //时间戳
    private String msgts;
    //报文数据信息
    private Object msg;

    public SystemInfoData() {
    }

    public SystemInfoData(String msgid, int msgtype, int systype, int sysid, int connectid, String sign, String msgts, Object msg) {
        this.msgid = msgid;
        this.msgtype = msgtype;
        this.systype = systype;
        this.sysid = sysid;
        this.connectid = connectid;
        this.sign = sign;
        this.msgts = msgts;
        this.msg = msg;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public int getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(int msgtype) {
        this.msgtype = msgtype;
    }

    public int getSystype() {
        return systype;
    }

    public void setSystype(int systype) {
        this.systype = systype;
    }

    public int getSysid() {
        return sysid;
    }

    public void setSysid(int sysid) {
        this.sysid = sysid;
    }

    public int getConnectid() {
        return connectid;
    }

    public void setConnectid(int connectid) {
        this.connectid = connectid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMsgts() {
        return msgts;
    }

    public void setMsgts(String msgts) {
        this.msgts = msgts;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"msgid\":\"")
                .append(msgid).append('\"');
        sb.append(",\"msgtype\":")
                .append(msgtype);
        sb.append(",\"systype\":")
                .append(systype);
        sb.append(",\"sysid\":")
                .append(sysid);
        sb.append(",\"connectid\":")
                .append(connectid);
        sb.append(",\"sign\":\"")
                .append(sign).append('\"');
        sb.append(",\"msgts\":\"")
                .append(msgts).append('\"');
        sb.append(",\"msg\":")
                .append(msg);
        sb.append('}');
        return sb.toString();
    }
}
