package com.shuzhi.entity.commandResult;

import com.fasterxml.jackson.annotation.JsonProperty;

public class XwalkLoadPageResultArgs {
    private String url;
    @JsonProperty("backupurl")
    private String backupUrl;
    private Object persistent;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBackupurl() {
        return backupUrl;
    }

    public void setBackupurl(String backupUrl) {
        this.backupUrl = backupUrl;
    }

    public Object getPersistent() {
        return persistent;
    }

    public void setPersistent(Object persistent) {
        this.persistent = persistent;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"url\":\"")
                .append(url).append('\"');
        sb.append(",\"backupUrl\":\"")
                .append(backupUrl).append('\"');
        sb.append(",\"persistent\":")
                .append(persistent);
        sb.append('}');
        return sb.toString();
    }
}
