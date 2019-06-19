package com.shuzhi.enums;
/**
* @Program: TypeTopicQueneEnums
* @Description: 
* @Author: YuJQ
* @Create: 2019/6/5 10:21
**/
public enum TypeTopicQueneEnums {

    abloomy(1001, "韵盛发"), spon(1002, "世邦"), tecnon(1003, "太龙"),lonbon(1004,"来邦");
    private String info;
    private int code;

    private TypeTopicQueneEnums(int code, String info) {
        this.info = info;
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public int getCode() {
        return code;
    }

    public static TypeTopicQueneEnums stateOf(int index) {
        for (TypeTopicQueneEnums code : values()) {
            if (code.getCode() == index) {
                return code;
            }
        }
        return null;
    }

}
