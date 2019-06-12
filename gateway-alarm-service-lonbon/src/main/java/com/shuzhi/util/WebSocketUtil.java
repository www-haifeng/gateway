package com.shuzhi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
/**
 * 开启WebSocket支持
 */
@Configuration
public class WebSocketUtil {
    private final static Logger logger = LoggerFactory.getLogger(WebSocketUtil.class);
    public static List<Session> socketClientCreate(Class clazz,String url) {
        List<Session> clientList = new ArrayList<>();
        try {
                WebSocketContainer container = ContainerProvider.getWebSocketContainer();
                Session session = container.connectToServer(clazz, new URI(url));
                clientList.add(session);

        }  catch (Exception e) {
            logger.info("websocket创建连接失败");
        }finally {
            return clientList;
        }
    }
}
