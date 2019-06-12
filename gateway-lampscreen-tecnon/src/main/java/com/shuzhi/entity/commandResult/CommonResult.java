package com.shuzhi.entity.commandResult;


public class CommonResult {
    private int result;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"result\":")
                .append(result);
        sb.append('}');
        return sb.toString();
    }
}
