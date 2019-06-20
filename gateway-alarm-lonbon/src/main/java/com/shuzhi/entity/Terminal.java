package com.shuzhi.entity;

import org.springframework.stereotype.Component;

@Component
public class Terminal {

    /**
     * 终端类型：0-主机，1-分机
     */
    private int terminalType;

    /**
     * 终端编号
     */
    private int displayNum;

    /**
     * 终端IP地址
     */
    private String netAddr;

    /**
     * 终端名称
     */
    private String name;

    /**
     * 终端型号
     */
    private String model;

    public Terminal() {
    }

    public int getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(int terminalType) {
        this.terminalType = terminalType;
    }

    public int getDisplayNum() {
        return displayNum;
    }

    public void setDisplayNum(int displayNum) {
        this.displayNum = displayNum;
    }

    public String getNetAddr() {
        return netAddr;
    }

    public void setNetAddr(String netAddr) {
        this.netAddr = netAddr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
