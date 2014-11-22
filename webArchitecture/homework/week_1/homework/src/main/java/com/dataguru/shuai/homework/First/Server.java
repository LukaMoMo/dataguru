package com.dataguru.shuai.homework.First;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Server {
	static final Log log = LogFactory.getLog(Server.class);

	private int port = 9999;

	public Server(int port) {
		port = 9999;
	}

	public void start() throws Exception {
		EventLoopGroup eg = new NioEventLoopGroup();
		try {
			ServerBootstrap sb = new ServerBootstrap();
			sb.group(eg).channel(NioServerSocketChannel.class)
					.localAddress("0.0.0.0", port);
			sb.childHandler(new ChannelInitializer<Channel>() {

				@Override
				protected void initChannel(Channel ch) throws Exception {
					ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
						@Override
						public void channelRead(ChannelHandlerContext ctx,
								Object msg) throws Exception {
							System.out.println("Server receive :" + msg);
							ctx.writeAndFlush(msg);
						}
					});
				}

				@Override
				public void channelReadComplete(ChannelHandlerContext ctx)
						throws Exception {
					System.out.println("read complete");

					ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(
							ChannelFutureListener.CLOSE);
				}

				@Override
				public void exceptionCaught(ChannelHandlerContext ctx,
						Throwable cause) throws Exception {
					cause.printStackTrace();
					ctx.close();
				}
			});

			ChannelFuture cf = sb.bind().sync();
			System.out.println(Server.class.getName() + "started andlistenon "
					+ cf.channel().localAddress());
			cf.channel().closeFuture().sync();
		} catch (Exception e) {
			eg.shutdownGracefully().sync();
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		new Server(9999).start();
	}

}
