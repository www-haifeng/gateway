package com.shuzhi.entity.commandResult;

/**
 * @Description: 2.1.使用xwalk加载网页(支持H5) 封装返回值
 * @Author: YHF
 * @date 2019/6/11
 */
public class XwalkLoadPageResult {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":\"")
                .append(type).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
