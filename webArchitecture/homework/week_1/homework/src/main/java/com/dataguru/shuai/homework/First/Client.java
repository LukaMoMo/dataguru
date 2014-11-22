package com.dataguru.shuai.homework.First;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Client {
	static final Log log = LogFactory.getLog(Client.class);
	private String host;
	private int port;

	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void start() throws Exception {
		EventLoopGroup eg = new NioEventLoopGroup();
		try {
			Bootstrap bt = new Bootstrap();
			bt.group(eg).channel(NioSocketChannel.class)
					.remoteAddress(host, port);
			bt.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(
							new SimpleChannelInboundHandler<ByteBuf>() {

								@Override
								public void channelActive(
										ChannelHandlerContext ctx)
										throws Exception {
									ctx.write(Unpooled.copiedBuffer("I'm OK",
											CharsetUtil.UTF_8));
								}

								@Override
								protected void channelRead0(
										ChannelHandlerContext ctx, ByteBuf msg)
										throws Exception {
									System.out.println("client receiver"
											+ ByteBufUtil.hexDump(msg
													.readBytes(msg
															.readableBytes())));
								}

								@Override
								public void exceptionCaught(
										ChannelHandlerContext ctx,
										Throwable cause) throws Exception {
									cause.printStackTrace();
									ctx.close();
								}

							});
				}

			});

			ChannelFuture cf = bt.connect().sync();
			cf.channel().close().sync();
		} catch (Exception e) {
			// TODO: handle exception
			eg.shutdownGracefully().sync();
		}
	}

	public static void main(String[] args) throws Exception {
		new Client("127.0.0.1", 9999).start();
		
	}
}
