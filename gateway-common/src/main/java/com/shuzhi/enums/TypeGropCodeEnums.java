package com.shuzhi.enums;
/**
* @Program: TypeGropCodeEnums
* @Description: 
* @Author: YuJQ
* @Create: 2019/6/5 10:21
**/
public enum TypeGropCodeEnums {

    lowerControlMessage(1, "下控通道"), wifiMessage(2, "WIFI通道"), alarmMessage(3, "告警通道"), upMessage(4, "上报通道");
    private String info;
    private int code;

    private TypeGropCodeEnums(int code, String info) {
        this.info = info;
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public int getCode() {
        return code;
    }

    public static TypeGropCodeEnums stateOf(int index) {
        for (TypeGropCodeEnums code : values()) {
            if (code.getCode() == index) {
                return code;
            }
        }
        return null;
    }

   /* public static void main(String[] args) {
        System.out.println(TypeGropCodeEnums.stateOf(1).getInfo());
    }
*/
}
