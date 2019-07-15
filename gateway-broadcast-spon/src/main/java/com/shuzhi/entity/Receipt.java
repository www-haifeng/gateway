package com.shuzhi.entity;

import com.alibaba.fastjson.JSONArray;

public class Receipt {
    //是否调用成功
    private int res;
    //终端总数
    private int total;
    //终端数据内容
    private JSONArray rows;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public JSONArray getRows() {
        return rows;
    }

    public void setRows(JSONArray rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"res\":")
                .append(res);
        sb.append(",\"total\":")
                .append(total);
        sb.append(",\"rows\":")
                .append(rows);
        sb.append('}');
        return sb.toString();
    }
}
