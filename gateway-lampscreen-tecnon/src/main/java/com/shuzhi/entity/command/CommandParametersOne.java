package com.shuzhi.entity.command;

/**
 * @Description: 通用参数(type、fn、arg1)
 *               1.2开关屏幕
 * @Author: YHF
 * @date 2019/6/11
 */
public class CommandParametersOne{
    private String type;
    private String fn;
    private Object arg1;

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

    public Object getArg1() {
        return arg1;
    }

    public void setArg1(Object arg1) {
        this.arg1 = arg1;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"fn\":\"")
                .append(fn).append('\"');
        sb.append(",\"arg1\":")
                .append(arg1);
        sb.append('}');
        return sb.toString();
    }
}
