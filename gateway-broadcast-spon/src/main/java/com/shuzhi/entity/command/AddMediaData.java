package com.shuzhi.entity.command;

public class AddMediaData {
    private String file;
    private String subpath;
    private String unformat;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getSubpath() {
        return subpath;
    }

    public void setSubpath(String subpath) {
        this.subpath = subpath;
    }

    public String getUnformat() {
        return unformat;
    }

    public void setUnformat(String unformat) {
        this.unformat = unformat;
    }
}
