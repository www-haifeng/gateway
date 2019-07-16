package com.shuzhi.entity;

public class ReportResult {

    private int result;
    private String id;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"result\":")
                .append(result);
        sb.append(",\"id\":")
                .append(id);
        sb.append('}');
        return sb.toString();
    }
}
