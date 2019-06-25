package com.shuzhi.webSocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class WiFiWebSocketClient extends WebSocketClient {
    private final static Logger logger = LoggerFactory.getLogger(WiFiWebSocketClient.class);

    public WiFiWebSocketClient(URI serverUri ) {
        super( serverUri );
    }

    @Override
    public void onOpen( ServerHandshake handshakedata ) {
        System.out.println( "Connected" );

    }

    @Override
    public void onMessage( String message ) {
        System.out.println( "got: " + message );

    }

    @Override
    public void onClose( int code, String reason, boolean remote ) {
        System.out.println( "Disconnected" );

    }

    @Override
    public void onError( Exception ex ) {
        ex.printStackTrace();

    }
}
