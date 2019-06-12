package com.shuzhi.thread.handle;

import com.shuzhi.entity.WebSocketEntity;
import com.shuzhi.util.ConstantUtils;

import java.util.concurrent.ExecutorService;

/**
* @Program: 线程执行
* @Description:
* @Author: YuJQ
* @Create: 2019/6/10 15:16
**/
public class ExecuteThread {



    //发送
    public static void executeSendThread(ExecutorService executorService,WebSocketEntity webSocketEntity) {
        executorService.execute(new Runnable()  {
            @Override
            public void run(){
                    if(webSocketEntity.getSession().isOpen()){
                        webSocketEntity.getSession().getAsyncRemote().sendText(webSocketEntity.getMessage());
                    }

            }
        });
    }
    //接收
//    public static void executeReceiveThread(ExecutorService executorService,WebSocketEntity webSocketEntity){
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                if(webSocketEntity.getSession().isOpen()){
//                    webSocketEntity.getSession().getAsyncRemote().sendText(webSocketEntity.getReceiveMessage());
//                }
//            }
//        });
//    }

}
