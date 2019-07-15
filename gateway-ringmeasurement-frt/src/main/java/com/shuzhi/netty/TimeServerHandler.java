package com.shuzhi.netty;

import com.shuzhi.cache.Cache;
import com.shuzhi.common.ByteUtils;
import com.shuzhi.common.SpringUtil;
import com.shuzhi.service.CommandService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

@Slf4j
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    private static CommandService commandService;

    {
        commandService = SpringUtil.getBean(CommandService.class);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //加入缓存 ,客户端ip作为Key
        System.out.println("终端连接:---" + ctx);
        InetSocketAddress ipSocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = ipSocket.getAddress().getHostAddress();
        System.out.println(clientIp);
        Cache.channelCache.put(clientIp, ctx);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("----接收到数据----");
        byte[] msgBytes = (byte[]) msg;
        ByteUtils ByteUtil = new ByteUtils();
        String s = ByteUtil.bytesToHexString(msgBytes);
        System.out.println(s);
        if (Cache.sendOneByte == null) {
            Cache.sendOneByte = msgBytes;
        } else {
            commandService.handleResult(msgBytes);
        }
    }
}
