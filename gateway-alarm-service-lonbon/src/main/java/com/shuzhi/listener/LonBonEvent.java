package com.shuzhi.listener;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * Created by ztt on 2019/6/11
 **/
@Data
public class LonBonEvent extends ApplicationEvent {
    private String msg;
    public LonBonEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

}
