package morning.cat.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import morning.cat.netty.client.handle.EchoClientHandler;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/4/23 3:23 PM
 */
public class ClientMain {

    private static final int MAX_RETRY = 16;

    public static void main(String[] args) {
        // 用来处理I/O操作的多线程事件循环器
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 客户端
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    ChannelPipeline channelPipeline = channel.pipeline();
                    channelPipeline.addLast("ClientHandle", new EchoClientHandler());
                }
            });
            // 设置参数
            bootstrap.option(ChannelOption.SO_BACKLOG, 128);

            // 连接服务端
            ChannelFuture channelFuture = connect(bootstrap, "127.0.0.1", 51001, MAX_RETRY);
            System.out.println("Netty4 Client start...");
            //channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            workerGroup.shutdownGracefully();
        }
    }

    private static ChannelFuture connect(Bootstrap bootstrap, String host, int port, int retry) throws InterruptedException {
        ChannelFuture channelFuture = bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");
            } else if (retry == 0) {
                System.err.println("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
        return channelFuture;
    }
}







