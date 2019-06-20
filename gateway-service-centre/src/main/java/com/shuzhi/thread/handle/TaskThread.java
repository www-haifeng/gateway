package com.shuzhi.thread.handle;

import com.shuzhi.entity.WebSocketEntity;

import java.util.concurrent.Callable;

public  class  TaskThread implements Callable<Boolean> {
    private WebSocketEntity webSocketEntity;
    public TaskThread(WebSocketEntity webSocketEntity){
        this.webSocketEntity= webSocketEntity;
    }
    public Boolean call() {
        if(webSocketEntity.getSession().isOpen()){
            webSocketEntity.getSession().getAsyncRemote().sendText(webSocketEntity.getMessage());
            return true;
        }else{
            return false;
        }
    }
}
