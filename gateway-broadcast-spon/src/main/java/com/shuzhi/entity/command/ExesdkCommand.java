package com.shuzhi.entity.command;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ExesdkCommand {
    private String source;
    private String target;
    private Integer commandtype;
    private Integer isstop;
    private String user = "admin";
    private String extdata;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getCommandtype() {
        return commandtype;
    }

    public void setCommandtype(Integer commandtype) {
        this.commandtype = commandtype;
    }

    public Integer getIsstop() {
        return isstop;
    }

    public void setIsstop(Integer isstop) {
        this.isstop = isstop;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getExtdata() {
        return extdata;
    }

    public void setExtdata(String extdata) {
        this.extdata = extdata;
    }

  /*  @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("jsondata%5Bsource%5D=")
                .append(source);
        sb.append("&jsondata%5Btarget%5D=")
                .append(target);
        sb.append("&jsondata%5Bcommandtype%5D=")
                .append(commandtype);
        sb.append("&jsondata%5Bisstop%5D=")
                .append(isstop);
        sb.append("&jsondata%5Buser%5D=")
                .append(user);
        sb.append("&jsondata%5Bextdata%5D=")
                .append(extdata);
        sb.append("&jsondata%5Bhasvideo%5D=")
                .append(hasvideo);
        return sb.toString();
    }*/

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        try {
            sb.append("jsondata%5Bsource%5D=")
                    .append(URLEncoder.encode((source), "utf-8"));
            sb.append("&jsondata%5Btarget%5D=")
                    .append(URLEncoder.encode((target), "utf-8"));
            sb.append("&jsondata%5Bcommandtype%5D=")
                    .append(commandtype);
            sb.append("&jsondata%5Bisstop%5D=")
                    .append(isstop);
            sb.append("&jsondata%5Buser%5D=")
                    .append(URLEncoder.encode((user), "utf-8"));
            sb.append("&jsondata%5Bextdata%5D=")
                    .append(URLEncoder.encode((extdata), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
