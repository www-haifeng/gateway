package com.shuzhi.conusmer;

import lombok.extern.slf4j.Slf4j;

import javax.websocket.*;

/**
* @Program: websocket客户端连接上层服务
* @Description: 
* @Author: YuJQ
* @Create: 2019/6/5 10:38
**/
@Slf4j
@ClientEndpoint
public class WSClientService {
    @OnOpen
    public void onOpen(Session session) {
        log.info("客户端建立连接成功");
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("Client onMessage: " + message);
    }

    @OnClose
    public void onClose() {
        log.info("客户端关闭");
    }
}
