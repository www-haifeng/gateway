package com.shuzhi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
* @Program: WebSocketUtil
* @Description:
* @Author: YuJQ
* @Create: 2019/6/5 10:54
**/
public class WebSocketUtil {
    private final static Logger logger = LoggerFactory.getLogger(WebSocketUtil.class);
    private final static AtomicInteger atomicInteger = new AtomicInteger(0);
    public static Session socketClientCreate(Class clazz,String url) {
        Session session =null;
        try {
                WebSocketContainer container = ContainerProvider.getWebSocketContainer();
                 session = container.connectToServer(clazz, new URI(url));
                atomicInteger.set(0);
        }  catch (Exception e) {
            atomicInteger.incrementAndGet();
            logger.info("websocket创建连接失败, Class:"+clazz+", 地址:"+url +",连接次数:"+atomicInteger.get());
            try {
                Thread.sleep(Long.parseLong(1000 * atomicInteger.get()+""));
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            logger.info(atomicInteger.get() +"秒后 websocket重新创建连接 Class:"+clazz+", 地址:"+url);
            WebSocketUtil.socketClientCreate(clazz,url);
        }
        return session;

    }
}
