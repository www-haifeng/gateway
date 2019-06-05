package com.shuzhi.service.handler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

/**
* @Program: TCPServer
* @Description: 开启netty服务
* @Author: YuJQ
* @Create: 2019/4/10 10:39
**/
@Component
public class TCPServer {
	@Autowired
	@Qualifier("serverBootstrap")
	private ServerBootstrap serverBootstrap;

	@Autowired
	@Qualifier("tcpSocketAddress")
	private InetSocketAddress tcpPort;

	private Channel serverChannel;
    //绑定端口号  端口号8090
	public void start() throws Exception {
		serverChannel =  serverBootstrap.bind(tcpPort).sync().channel().closeFuture().sync().channel();
		//serverChannel =  serverBootstrap.bind(tcpPort).sync().channel();

	}

	@PreDestroy
	public void stop() throws Exception {
		serverChannel.close();
		serverChannel.parent().close();
	}

	public ServerBootstrap getServerBootstrap() {
		return serverBootstrap;
	}

	public void setServerBootstrap(ServerBootstrap serverBootstrap) {
		this.serverBootstrap = serverBootstrap;
	}

	public InetSocketAddress getTcpPort() {
		return tcpPort;
	}

	public void setTcpPort(InetSocketAddress tcpPort) {
		this.tcpPort = tcpPort;
	}
}
