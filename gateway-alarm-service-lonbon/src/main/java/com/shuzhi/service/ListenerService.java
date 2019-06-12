package com.shuzhi.service;

import com.shuzhi.listener.LonBonEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by ztt on 2019/6/12
 **/
@Service
public class ListenerService {
    @Autowired
    private ApplicationContext applicationContext;
    public void publish(String msg) {
        System.out.println(applicationContext.getClass());
        //通过上下文对象发布监听
        for (int i = 0; i < 2; i++) {
            applicationContext.publishEvent(new LonBonEvent(this, msg + i));
        }
    }

}
