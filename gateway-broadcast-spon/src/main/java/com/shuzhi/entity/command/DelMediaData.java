package com.shuzhi.entity.command;

import com.fasterxml.jackson.annotation.JsonProperty;

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
                .append(fileName);
        return sb.toString();
    }
}

