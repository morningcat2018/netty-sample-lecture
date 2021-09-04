package morning.cat.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
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

            // attr()方法可以给服务端的 channel，也就是NioServerSocketChannel指定一些自定义属性，
            // 然后我们可以通过channel.attr()取出这个属性
            serverBootstrap.attr(AttributeKey.newInstance("serverName"), "nettyServer");
            serverBootstrap.childAttr(AttributeKey.newInstance("clientKey"), "clientValue");

            // bossGroup handle
            // 指定在服务端启动过程中的一些逻辑
            serverBootstrap.handler(new ChannelInitializer<NioServerSocketChannel>() {
                protected void initChannel(NioServerSocketChannel ch) {
                    System.out.println("服务端启动中");
                }
            });

            // workerGroup handle
            // 指定处理新连接数据的读写处理逻辑
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
            serverBootstrap
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 绑定端口
            // telnet 127.0.0.1 51001
            ChannelFuture channelFuture = bind(serverBootstrap, 51001);
            //System.out.println("Netty4 Server start...");
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    private static ChannelFuture bind(final ServerBootstrap serverBootstrap, final int port) {
        ChannelFuture channelFuture = serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) {
                if (future.isSuccess()) {
                    System.out.println("端口[" + port + "]绑定成功!");
                } else {
                    System.err.println("端口[" + port + "]绑定失败!");
                    bind(serverBootstrap, port + 1);
                }
            }
        });
        return channelFuture;
    }

}
