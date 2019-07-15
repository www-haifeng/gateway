package com.shuzhi.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeServer  {


    public void bind(int port){
        log.info("netty服务启动,端口为:"+port);
        /*
        * 配置服务端的 NIO 线程池,用于网络事件处理，实质上他们就是 Reactor 线程组
        * bossGroup 用于服务器接受客户端连接
        * workerGroup 用于进行socketChannel 网络读写
        *
        * */
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
        //serverBootstrap 是netty用于NIO服务端的启动辅助类
        ServerBootstrap serverBootstrap = new ServerBootstrap();
         serverBootstrap.group(bossGroup, workerGroup)
                 .channel(NioServerSocketChannel.class)
                 .option(ChannelOption.SO_BACKLOG,1024)
                 .childHandler(new ChildChannelHandler());
         //配置完服务端启动辅助类后，调用bind方法绑定端口，调用sync方法同步等待绑定操作完成
            ChannelFuture f = serverBootstrap.bind(port).sync();
            System.out.println(Thread.currentThread().getName()+":" +
                    "服务器开始监听端口，等待客户端连接·····");

            //下面会进行阻塞，等待服务端连接关闭之后，main方法退出。
            f.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast();
            socketChannel.pipeline().addLast(new ByteArrayDecoder());
            socketChannel.pipeline().addLast(new ByteArrayEncoder());
            socketChannel.pipeline().addLast(new TimeServerHandler());
        }
    }
}
