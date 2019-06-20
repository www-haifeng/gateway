package com.shuzhi.enums;
/**
* @Program: TypeTopicExchangeEnums
* @Description: 
* @Author: YuJQ
* @Create: 2019/6/5 10:21
**/
public enum TypeTopicExchangeEnums {

    wifiexchange(1001, "WiFi设备"), bcexchange(1002, "广播设备"), lightexchange(1003, "灯杆屏设备"),alarmexchange(1004,"来邦");
    private String info;
    private int code;

    private TypeTopicExchangeEnums(int code, String info) {
        this.info = info;
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public int getCode() {
        return code;
    }

    public static TypeTopicExchangeEnums stateOf(int index) {
        for (TypeTopicExchangeEnums code : values()) {
            if (code.getCode() == index) {
                return code;
            }
        }
        return null;
    }

}
