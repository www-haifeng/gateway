package com.shuzhi.util;

import javax.websocket.Session;

/**
* @Program: session管理
* @Description:
* @Author: YuJQ
* @Create: 2019/6/6 13:27
**/

public class SessionManage {
    //判断session
    public static Boolean sessionStatus(Session session){
        Boolean flug =false;
        if(session != null && session.isOpen()){
            flug = true;
        }
        return flug;

    }




}
