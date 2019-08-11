package com.shuzhi.entity;
/**
 * @Description: 消息回执二层数据
 * @Author:     lirb
 * @CreateDate:   2019/8/9 11:41
 * @Version:   1.0
 **/
public class MessageRevertData {
    //时间戳
    private String timestamp;
    //命令码
    private int code;
    //数据内容
    private String data;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"timestamp\":\"")
                .append(timestamp).append('\"');
        sb.append(",\"code\":")
                .append(code);
        sb.append(",\"data\":")
                .append(data);
        sb.append('}');
        return sb.toString();
    }
}
