package com.shuzhi.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by ztt on 2019/6/11
 **/
@Component
public class LonBonListener {

    @EventListener
    @Async
    public void listener1(LonBonEvent event) {
        System.out.println("注解事件监听-1：" + event.getMsg());
    }

    @EventListener
    @Async
    public void listener2(LonBonEvent event) {
        System.out.println("注解事件监听-2：" + event.getMsg());
    }
}
