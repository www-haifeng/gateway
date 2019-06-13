package com.shuzhi.service.impl;

import com.sun.jna.Structure;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ztt on 2019/6/11
 **/
public class TerminalInfo extends Structure {

    public static class ByReference extends TerminalInfo implements Structure.ByReference{}

    public static class ByValue extends TerminalInfo implements Structure.ByValue{}

    public int terminalType =1000;
    public int displayNum = 1000;
    public byte[] netAddr = new byte[32];
    public byte[] name = new byte[128];
    public byte[] model = new byte[64];

    @Override
    protected List getFieldOrder() {

        List<String> Field = new ArrayList<String>();
        Field.add("terminalType");
        Field.add("displayNum");
        Field.add("netAddr");
        Field.add("name");
        Field.add("model");
        return Field;
    }
}
