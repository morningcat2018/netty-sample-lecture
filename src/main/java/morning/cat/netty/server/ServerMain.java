package morning.cat.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import morning.cat.netty.server.handle.EchoServerHandle;

public class ServerMain {

	public static void main(String[] args) {

		// 用来处理I/O操作的多线程事件循环器
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			// 服务端
			ServerBootstrap serverBootstrap = new ServerBootstrap();

			// 设置 NioSocket 工厂
			serverBootstrap.group(bossGroup, workerGroup);
			serverBootstrap.channel(NioServerSocketChannel.class);
			// 设置管道工厂 ChannelHandler
			serverBootstrap.childHandler(new ChannelInitializer<Channel>() {
				@Override
				protected void initChannel(Channel channel) throws Exception {

					ChannelPipeline channelPipeline = channel.pipeline();
					// 接收信息转换成string(上行)
					// channelPipeline.addLast("StringDecoder", new StringDecoder());
					// 回写直接写入字符串
					// channelPipeline.addLast("StringEncoder", new StringEncoder());

					//
					// channelPipeline.addLast("EchoServerHandle", new EchoServerHandle());
					//
					// channelPipeline.addLast("HelloHandle", new HelloHandle());

					//
					channelPipeline.addLast("HelloHandle", new EchoServerHandle());

					// channelPipeline.addLast("TimeServerHandler", new TimeServerHandler());
				}
			});
			// 设置参数
			serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);
			serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);

			// 绑定端口
			// telnet 127.0.0.1 51001
			// Bind and start to accept incoming connections.
			ChannelFuture channelFuture = serverBootstrap.bind(51001).sync();
			System.out.println("Netty4 Server start...");

			// Wait until the server socket is closed.
			// In this example, this does not happen, but you can do that to gracefully
			// shut down your server.
			channelFuture.channel().closeFuture().sync();
			System.out.println("closeFuture");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 关闭资源
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

}
