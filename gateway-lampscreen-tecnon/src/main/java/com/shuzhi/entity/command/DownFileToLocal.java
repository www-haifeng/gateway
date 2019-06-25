package com.shuzhi.entity.command;

public class DownFileToLocal {

    private String type;
    private String url;
    private String path;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"url\":\"")
                .append(url).append('\"');
        sb.append(",\"path\":\"")
                .append(path).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
