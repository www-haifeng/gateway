package com.shuzhi.netty;

import com.shuzhi.common.ByteUtils;
import com.shuzhi.common.SpringUtil;
import com.shuzhi.service.CommandService;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    private final static Logger logger = LoggerFactory.getLogger(CommandService.class);

    private static CommandService commandService;
    static {
        commandService = SpringUtil.getBean(CommandService.class);
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg)
            throws Exception {
		System.out.println("接收到数据:"+msg);
        ByteBuf buf =msg.copy().content();
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        commandService.WiFiStyletService(bytes);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.close();
        cause.printStackTrace();
    }
}
