package com.shuzhi.entity.command;

/**
 * @Description: 通用参数(type、fn)
 *               1.1获取屏幕开关状态使用
 * @Author: YHF
 * @date 2019/6/11
 */
public class CommonParameters {
    private String type;
    private String fn;

    public CommonParameters(String type, String fn) {
        this.type = type;
        this.fn = fn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"fn\":\"")
                .append(fn).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
