package morning.cat.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import morning.cat.netty.client.handle.EchoClientHandler;

import java.util.Scanner;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/4/23 3:23 PM
 */
public class ClientMain {

    public static void main(String[] args) throws InterruptedException {
        // 用来处理I/O操作的多线程事件循环器
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {

            // 客户端
            Bootstrap clientBootstrap = new Bootstrap();

            // 设置 NioSocket 工厂
            clientBootstrap.group(workerGroup);
            clientBootstrap.channel(NioSocketChannel.class);


            // 设置管道工厂
            clientBootstrap.handler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel channel) throws Exception {

                    ChannelPipeline channelPipeline = channel.pipeline();
                    channelPipeline.addLast("ClientHandle", new EchoClientHandler());
                }
            });

            // 设置参数
            clientBootstrap.option(ChannelOption.SO_BACKLOG, 128);

            // 连接服务端
            ChannelFuture channelFuture = clientBootstrap.connect("127.0.0.1", 51001).sync();
            System.out.println("Netty4 Client start...");


            //channelFuture.channel().closeFuture().sync();

//            Channel channel = channelFuture.channel();
//            Scanner scanner = new Scanner(System.in);
//            while (true) {
//                System.out.print("请输入：");
//                String ss = scanner.next();
//                if (ss.equals("exit")) {
//                    break;
//                }
//                channel.writeAndFlush(ss);
//            }


        } finally {
            // 关闭资源
            workerGroup.shutdownGracefully();
        }
    }
}







