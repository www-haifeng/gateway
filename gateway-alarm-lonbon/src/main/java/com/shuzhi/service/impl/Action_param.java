package com.shuzhi.service.impl;

import com.sun.jna.Structure;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ztt on 2019/6/11
 **/
@Service
public class Action_param extends Structure {
    public static class ByReference extends Action_param implements Structure.ByReference {
    }

    public static class ByValue extends Action_param implements Structure.ByValue {
    }

    public int sender = 1;
    public int receiver = 100;
    public byte[] acceptBc = new byte[1024];
    public byte[] SessionId = new byte[128];
    public int broadId = 1000;
    public byte[] rdFile = new byte[512];
    public int atmTerNum = 0;

    @Override
    protected List getFieldOrder() {

        List<String> Field = new ArrayList<String>();
        Field.add("sender");
        Field.add("receiver");
        Field.add("acceptBc");
        Field.add("SessionId");
        Field.add("broadId");
        Field.add("rdFile");
        Field.add("atmTerNum");
        return Field;
    }
}
