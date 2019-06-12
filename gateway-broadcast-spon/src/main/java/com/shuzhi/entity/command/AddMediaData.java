package com.shuzhi.entity.command;

public class AddMediaData {
    private String filename;
    private String filepath;
    private String subpath;
    private String unformat;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
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
