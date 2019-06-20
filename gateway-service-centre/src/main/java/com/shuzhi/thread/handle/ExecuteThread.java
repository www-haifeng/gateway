package com.shuzhi.thread.handle;

import com.shuzhi.entity.WebSocketEntity;
import com.shuzhi.util.ConstantUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
* @Program: 线程执行
* @Description:
* @Author: YuJQ
* @Create: 2019/6/10 15:16
**/
public class ExecuteThread {



    //发送
    public static  Boolean executeSendThread(ExecutorService executorService,WebSocketEntity webSocketEntity) {
        Future<Boolean> future = executorService.submit(new TaskThread(webSocketEntity));
        boolean flug = false;
        try {
             flug = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  flug;
//       executorService.execute(new Runnable()  {
//            @Override
//            public void run(){
//                    if(webSocketEntity.getSession().isOpen()){
//                        webSocketEntity.getSession().getAsyncRemote().sendText(webSocketEntity.getMessage());
//                    }
//            }
//        });

    }


}
