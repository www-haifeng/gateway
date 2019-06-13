package com.shuzhi.service.handler;

import com.alibaba.fastjson.JSON;
import com.shuzhi.producer.RabbitSender;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class MyWebSockeHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker webSocketServerHandshaker;
    private ChannelRepository channelRepository;
    private RabbitSender rabbitSender;
    public MyWebSockeHandler(){ }
    public MyWebSockeHandler(ChannelRepository channelRepository, RabbitSender rabbitSender){
        this.channelRepository = channelRepository;
        this.rabbitSender = rabbitSender;
    }

    //客户端与服务端创建链接的时候调用
    @Override
    public void channelActive (ChannelHandlerContext context)throws Exception{
        channelRepository.put(context.channel().id().toString(),context.channel());
       // System.out.println(context.channel().remoteAddress());
        System.out.println("客户端与服务端连接开启" + LocalDateTime.now());

    }
    //客户端与服务端断开连接的时候调用
    @Override
    public void channelInactive(ChannelHandlerContext context)throws Exception{
        channelRepository.remove(context.channel().id().toString());
        System.out.println("客户端与服务端连接断开" + LocalDateTime.now());
    }
    //服务端接收客户端发送过来的数据结束之后调用
    @Override
    public void channelReadComplete(ChannelHandlerContext context)throws Exception{
        context.flush();
    }
    //工程出现异常的时候调用
    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable throwable)throws Exception{
        System.out.println("客户端与服务端连接开启" + LocalDateTime.now());
        throwable.printStackTrace();
        context.close();
    }
    //服务端处理客户端websocke请求的核心方法
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        //处理客户端向服务端发起的http握手请求
        if (o instanceof FullHttpRequest){
            handHttpRequest(channelHandlerContext,(FullHttpRequest) o);
        }else if (o instanceof WebSocketFrame){//处理websocket链接业务
            handWebSocketFrame(channelHandlerContext,(WebSocketFrame) o);
        }
    }

    /**
     * 处理客户端与服务端之间的websocket业务
     * @param context
     * @param webSocketFrame
     */
    private void handWebSocketFrame(ChannelHandlerContext context,WebSocketFrame webSocketFrame){
        if (webSocketFrame instanceof CloseWebSocketFrame){//判断是否是关闭websocket的指令
            webSocketServerHandshaker.close(context.channel(),(CloseWebSocketFrame) webSocketFrame.retain());
        }
        if (webSocketFrame instanceof PingWebSocketFrame){//判断是否是ping消息
            context.channel().write(new PongWebSocketFrame(webSocketFrame.content().retain()));
            return;
        }
        if (!(webSocketFrame instanceof TextWebSocketFrame)){//判断是否是二进制消息
            System.out.println("不支持二进制消息");
            throw new RuntimeException(this.getClass().getName());
        }
//        Map map = new HashMap<String,Object>();
//        map.put("a",1);
//        map.put("b",2);
//        map.put("c",3);
        String a = "{" +
                "\"msgid\": \"b7e84115-3aae-4d72-b49a-ec91a8847482\"," +
                "\"msgtype\": 0," +
                "\"systype\": 1001," +
                "\"sysid\": 1," +
                "\"connectid\": 1," +
                "\"sign\": \"4634e0d2f0b2b423936eb7651eacc54b98cb248f\"," +
                "\"msgts\": \"2015-03-05 17:59:00.567\"," +
                "\"msg\": {" +
                "\"overtime\": 5," +
                "\"type\": 1002," +
                "\"subtype\": 1002," +
                "\"did\": \"867725032979092\"," +
                "\"cmdid\": \"10003\"," +
                "\"data\":{" +
                "\"tid\":2," +
                "\"tname\":\"音响2\","+
                "\"tbcoutv\":2," +
                "\"tbcinv\":2," +
                "\"ttalkoutv\":2," +
                "\"ttalkinv\":2," +
                "\"tbcttype\":1," +
                "\"bctrecvtype\":1," +
                "\"tterminallevel\":0," +
                "\"tcalltimeout\":30," +
                "\"tcalltarget1\":\"1\"," +
                "\"tcalltarget2\":\"1\"," +
                "\"tbctlevel\":4," +
                "\"ttalklevel\":3," +
                "\"tmonlevel\":2," +
                "\"tmeetinglevel\":1," +
                "\"tbctlist\":\"*\"," +
                "\"ttalklist\":\"*\"," +
                "\"tmonlist\":\"*\"," +
                "\"tmeetinglist\":\"*\"," +
                "\"trtspurl\":\"\"," +
                "\"tpass\":1234"+
                "}" +
                "}" +
                "}";
        context.channel().writeAndFlush(new TextWebSocketFrame(a));

        //服务端向每个连接上来的客户端发送消息


        String request = ((TextWebSocketFrame) webSocketFrame ).text();
        Map<Object,Object> maps = (Map) JSON.parse(request);
        System.out.println("服务端收到客户端的消息：" + request);

/*
        //返回应答消息
        //获取客户端向服务端发送的消息
        String request = ((TextWebSocketFrame) webSocketFrame ).text();
        System.out.println("服务端收到客户端的消息：" + request);

        //发送mq 开始------------------------------
        //Map<Object,Object> maps = (Map) JSON.parse(request);
        Map<String,Object> maps11 = new HashMap<String,Object>();
        maps11.put("name","张三111");
        Map<String,Object> maps12 = new HashMap<String,Object>();
        maps11.put("name","张三22");
        for (Map.Entry<Object,Object>  maps1: maps.entrySet() ) {
            maps11.put(maps1.getKey().toString(),maps1.getValue());
        }*/
/*
        try {
           rabbitSender.send("upMessage","upMessage", "upMessage主题");
           rabbitSender.send("lowerControlMessage","lowerControlMessage", "lowerControlMessage主题");
            rabbitSender.send("wifiMessage","wifiMessage", "wifiMessage主题");
            rabbitSender.send("alarmMessage","alarmMessage", "alarmMessage主题");
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
    }




    /**
     * 处理客户端向服务端发起http握手请求业务
     * @param context
     * @param fullHttpRequest
     */
    private void handHttpRequest(ChannelHandlerContext context,FullHttpRequest fullHttpRequest){

    }

    /**
     * 服务端想客户端发送响应消息
     * @param context
     * @param fullHttpRequest
     * @param defaultFullHttpResponse
     */
    private void sendHttpResponse(ChannelHandlerContext context, FullHttpRequest fullHttpRequest, DefaultFullHttpResponse defaultFullHttpResponse){
        if (defaultFullHttpResponse.status().code() != 200){
            ByteBuf buf = Unpooled.copiedBuffer(defaultFullHttpResponse.getStatus().toString(), CharsetUtil.UTF_8);
            defaultFullHttpResponse.content().writeBytes(buf);
            buf.release();
        }
        //服务端向客户端发送数据
        ChannelFuture future = context.channel().writeAndFlush(defaultFullHttpResponse);
        if (defaultFullHttpResponse.status().code() !=200){
            future.addListener(ChannelFutureListener.CLOSE);
        }

    }

}
