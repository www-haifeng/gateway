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


/**
* @Program: WebSocketUtil
* @Description:
* @Author: YuJQ
* @Create: 2019/6/5 10:54
**/
public class WebSocketUtil {
    private final static Logger logger = LoggerFactory.getLogger(WebSocketUtil.class);
    public static Session socketClientCreate(Class clazz,String url) {

        try {
                WebSocketContainer container = ContainerProvider.getWebSocketContainer();
                Session session = container.connectToServer(clazz, new URI(url));
                return session;

        }  catch (Exception e) {
            logger.info("websocket创建连接失败");
        }finally {
            return null;
        }
        /*
        while (true) {
            for (int i = 0; i < num; ++i) {
                Session session = clientList.get(i);
                session.getBasicRemote().sendText("client+" + i);
            }
            Thread.sleep(2000);
        }
        */

    }
}
