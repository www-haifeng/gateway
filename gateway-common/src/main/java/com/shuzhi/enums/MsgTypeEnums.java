package com.shuzhi.enums;
/**
* @Program: MsyTypeEnums
* @Description: 
* @Author: YuJQ
* @Create: 2019/6/5 10:21
**/
public enum MsgTypeEnums {

    heartbeat (0, "心跳"),
    command(1, "命令"),
    receipt(2, "回执"),
    commandexecutionresult(3, "命令执行结果"),
    reportinginformation(4, "上报信息"),
    firstconnectionestablished(5, "首次建立连接"),
    jianlianreply(6, "建连回复"),
    alarminformation(7, "告警信息");



    private String info;
    private int code;

    private MsgTypeEnums(int code, String info) {
        this.info = info;
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public int getCode() {
        return code;
    }

    public static MsgTypeEnums stateOf(int index) {
        for (MsgTypeEnums code : values()) {
            if (code.getCode() == index) {
                return code;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(MsgTypeEnums.stateOf(3).getInfo() );
    }
}
