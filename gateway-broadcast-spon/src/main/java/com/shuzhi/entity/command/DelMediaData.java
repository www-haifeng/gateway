package com.shuzhi.entity.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shuzhi.commen.Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class DelMediaData {
    @JsonProperty("filename")
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("jsondata%5BfileName%5D=")
                .append(Utils.encodeUTF8(fileName));
        return sb.toString();
    }
/* @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("jsondata%5BfileName%5D=")
                .append(fileName);
        return sb.toString();
    }*/
}

