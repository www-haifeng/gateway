package com.shuzhi;


import com.shuzhi.producer.RabbitSender;
import com.shuzhi.service.handler.MyWebSockeHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
* @Program: ServerChannelInitializer
* @Description: application 中bootstarp调用
* @Author: YuJQ
* @Create: 2019/4/10 10:39
**/
@Component
@Qualifier("serverChannelInitializer")
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
	private final static int READER_IDLE_TIME_SECONDS = 20;//读操作空闲20秒
	private final static int WRITER_IDLE_TIME_SECONDS = 20;//写操作空闲20秒
	private final static int ALL_IDLE_TIME_SECONDS = 40;//读写全部空闲40秒
    private static final String WEBSOCKET_PATH = "/websocket";

    @Autowired
    public ChannelRepository channelRepository;
    @Autowired
    private RabbitSender rabbitSender;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p = socketChannel.pipeline();
        p.addLast(new HttpServerCodec());
        p.addLast(new HttpObjectAggregator(65536));
        p.addLast(new WebSocketServerCompressionHandler());
        p.addLast(new WebSocketServerProtocolHandler(WEBSOCKET_PATH, null, true));
        p.addLast(new MyWebSockeHandler(channelRepository,rabbitSender));


    }

}
