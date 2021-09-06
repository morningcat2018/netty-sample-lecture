package morning.cat.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import morning.cat.netty.client.handle.EchoClientHandler;
import morning.cat.netty.exception.IMException;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/4/23 3:23 PM
 */
public class ClientMain {

    private static final int MAX_RETRY = 8;

    /**
     * 127.0.0.1 51001
     *
     * @param args 0 host ,1 port
     */
    public static void main(String[] args) {
        ChannelFuture channelFuture = connect(args[0], check(args));
        while (true) {
            System.out.print(getCommandLine());
            String command = getCommand();
            switch (command) {
                case "0":
                    exitHandle(channelFuture);
                    break;
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                default:
                    System.out.println("指令错误，重新输入");
            }
        }

    }

    private static int check(String[] args) {
        if (args.length < 2) {
            throw new IMException("客户端入参错误");
        }
        try {
            return Integer.parseInt(args[1]);
        } catch (Exception e) {
            throw new IMException("客户端入参错误");
        }
    }

    private static ChannelFuture connect(String host, int port) {
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
            //bootstrap.option(ChannelOption.SO_BACKLOG, 128);

            // 连接服务端
            ChannelFuture channelFuture = connect(bootstrap, host, port, MAX_RETRY);
            //System.out.println("Netty4 Client start...");
            //channelFuture.channel().closeFuture().sync();
            return channelFuture;
        } finally {
            // 关闭资源
            workerGroup.shutdownGracefully();
        }
    }

    private static ChannelFuture connect(Bootstrap bootstrap, String host, int port, int retry) {
        ChannelFuture channelFuture = bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");
            } else if (retry < 2) {
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

    private static String getCommandLine() {
        StringBuilder builder = new StringBuilder();
        builder.append("====================================\n");
        builder.append("1. 登陆\n");
        builder.append("2. 发消息\n");
        builder.append("3. 注册\n");
        builder.append("0. 退出\n");
        builder.append("请输入：");
        return builder.toString();
    }

    private static String getCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();
        return command;
    }

    private static void exitHandle(ChannelFuture channelFuture) {
        System.out.println("退出登陆");
        try {
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}







