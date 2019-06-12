package com.shuzhi.entity.command;

public class ExesdkCommand {
    private String source;
    private String target;
    private int commandtype;
    private int isstop;
    private String user = "admin";
    private String extdata;
    private String hasvideo;

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

    public int getCommandtype() {
        return commandtype;
    }

    public void setCommandtype(int commandtype) {
        this.commandtype = commandtype;
    }

    public int getIsstop() {
        return isstop;
    }

    public void setIsstop(int isstop) {
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

    public String getHasvideo() {
        return hasvideo;
    }

    public void setHasvideo(String hasvideo) {
        this.hasvideo = hasvideo;
    }

    @Override
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
    }
}
