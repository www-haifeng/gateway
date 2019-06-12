package com.shuzhi.entity.command;

/**
 * @Description: 2.1.使用xwalk加载网页(支持H5)
 * @Author: YHF
 * @date 2019/6/11
 */
public class XwalkLoad {
    private String type = "callXwalkFn";
    private String fn;
    private Object arg;

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

    public Object getArg() {
        return arg;
    }

    public void setArg(Object arg) {
        this.arg = arg;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"fn\":\"")
                .append(fn).append('\"');
        sb.append(",\"arg\":")
                .append(arg);
        sb.append('}');
        return sb.toString();
    }
}
