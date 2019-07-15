package com.shuzhi.entity.command;

import com.shuzhi.commen.Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ExeRealPlayFile {
    private String rtype;
    private String param1;
    private String param2;
    private Integer param3;
    private String param4;
    private String param5;
    private Integer param6;
    private Integer param7;
    private Integer param8;
    private Integer param9;
    private Integer param10;
    private String extdata;

    public String getRtype() {
        return rtype;
    }

    public void setRtype(String rtype) {
        this.rtype = rtype;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public Integer getParam3() {
        return param3;
    }

    public void setParam3(Integer param3) {
        this.param3 = param3;
    }

    public String getParam4() {
        return param4;
    }

    public void setParam4(String param4) {
        this.param4 = param4;
    }

    public String getParam5() {
        return param5;
    }

    public void setParam5(String param5) {
        this.param5 = param5;
    }

    public Integer getParam6() {
        return param6;
    }

    public void setParam6(Integer param6) {
        this.param6 = param6;
    }

    public Integer getParam7() {
        return param7;
    }

    public void setParam7(Integer param7) {
        this.param7 = param7;
    }

    public Integer getParam8() {
        return param8;
    }

    public void setParam8(Integer param8) {
        this.param8 = param8;
    }

    public Integer getParam9() {
        return param9;
    }

    public void setParam9(Integer param9) {
        this.param9 = param9;
    }

    public Integer getParam10() {
        return param10;
    }

    public void setParam10(Integer param10) {
        this.param10 = param10;
    }

    public String getExtdata() {
        return extdata;
    }

    public void setExtdata(String extdata) {
        this.extdata = extdata;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("jsondata%5Brtype%5D=")
                .append(Utils.encodeUTF8(rtype));
        sb.append("&jsondata%5Bparam1%5D=")
                .append(Utils.encodeUTF8(param1));
        sb.append("&jsondata%5Bparam2%5D=")
                .append(Utils.encodeUTF8(param2));
        sb.append("&jsondata%5Bparam3%5D=")
                .append(param3);
        sb.append("&jsondata%5Bparam4%5D=")
                .append(Utils.encodeUTF8(param4));
        sb.append("&jsondata%5Bparam5%5D=")
                .append(Utils.encodeUTF8(param5));
        sb.append("&jsondata%5Bparam6%5D=")
                .append(param6);
        sb.append("&jsondata%5Bparam7%5D=")
                .append(param7);
        sb.append("&jsondata%5Bparam8%5D=")
                .append(param8);
        sb.append("&jsondata%5Bparam9%5D=")
                .append(param9);
        sb.append("&jsondata%5Bparam10%5D=")
                .append(param10);
        sb.append("&jsondata%5Bextdata%5D=")
                .append(Utils.encodeUTF8(extdata));
        return sb.toString();
    }

    /*@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("jsondata%5Brtype%5D=")
                .append(rtype);
        sb.append("&jsondata%5Bparam1%5D=")
                .append(param1);
        sb.append("&jsondata%5Bparam2%5D=")
                .append(param2);
        sb.append("&jsondata%5Bparam3%5D=")
                .append(param3);
        sb.append("&jsondata%5Bparam4%5D=")
                .append(param4);
        sb.append("&jsondata%5Bparam5%5D=")
                .append(param5);
        sb.append("&jsondata%5Bparam6%5D=")
                .append(param6);
        sb.append("&jsondata%5Bparam7%5D=")
                .append(param7);
        sb.append("&jsondata%5Bparam8%5D=")
                .append(param8);
        sb.append("&jsondata%5Bparam9%5D=")
                .append(param9);
        sb.append("&jsondata%5Bparam10%5D=")
                .append(param10);
        sb.append("&jsondata%5Bextdata%5D=")
                .append(extdata);
        return sb.toString();
    }*/
}
