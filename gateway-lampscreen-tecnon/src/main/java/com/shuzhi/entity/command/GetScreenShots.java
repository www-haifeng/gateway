package com.shuzhi.entity.command;

public class GetScreenShots {
    private String type = "callCardService";
    private String fn = "screenshot";
    private int arg1;
    private int arg2;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public int getArg1() {
        return arg1;
    }

    public void setArg1(int arg1) {
        this.arg1 = arg1;
    }

    public int getArg2() {
        return arg2;
    }

    public void setArg2(int arg2) {
        this.arg2 = arg2;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"fn\":\"")
                .append(fn).append('\"');
        sb.append(",\"arg1\":")
                .append(arg1);
        sb.append(",\"arg2\":")
                .append(arg2);
        sb.append('}');
        return sb.toString();
    }
}
