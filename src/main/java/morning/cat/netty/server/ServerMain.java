package morning.cat.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import morning.cat.netty.server.handle.MyChannelHandle;

public class ServerMain {

    public static void main(String[] args) {

        // 用来处理I/O操作的多线程事件循环器
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 服务端
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup);

            // channel
            serverBootstrap.channel(NioServerSocketChannel.class);
            // serverBootstrap.childHandler(null);

            // handler
            serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
            serverBootstrap.childHandler(
                    new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            ChannelPipeline channelPipeline = channel.pipeline();
                            channelPipeline.addLast(new HttpServerCodec());
                            channelPipeline.addLast(new MyChannelHandle());
                        }
                    }
            );

            // Bind and start to accept incoming connections.
            ChannelFuture channelFuture = serverBootstrap.bind(51001).sync();
            System.out.println("Netty4 Server start...");

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            channelFuture.channel().closeFuture().sync();
            System.out.println("closeFuture");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
