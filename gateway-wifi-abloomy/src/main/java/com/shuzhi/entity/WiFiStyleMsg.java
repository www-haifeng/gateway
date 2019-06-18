package com.shuzhi.entity;

public class WiFiStyleMsg {
    private int type;
    private int subtype;
    private String did;
    private String infoid;
    private String data;

    public WiFiStyleMsg(int type, int subtype, String did, String infoid, String data) {
        this.type = type;
        this.subtype = subtype;
        this.did = did;
        this.infoid = infoid;
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":")
                .append(type);
        sb.append(",\"subtype\":")
                .append(subtype);
        sb.append(",\"did\":\"")
                .append(did).append('\"');
        sb.append(",\"infoid\":\"")
                .append(infoid).append('\"');
        sb.append(",\"data\":")
                .append(data);
        sb.append('}');
        return sb.toString();
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
