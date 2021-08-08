package morning.cat.netty.client;

import com.google.protobuf.Any;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import morning.cat.netty.client.handle.ProtobufClientHandler;
import morning.cat.protos.StudentManager;

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
                    ChannelPipeline pipeline = channel.pipeline();
                    pipeline.addLast(new ProtobufVarint32FrameDecoder());
                    pipeline.addLast(new ProtobufDecoder(StudentManager.Student.getDefaultInstance()));
                    pipeline.addLast(new ProtobufDecoder(StudentManager.Teacher.getDefaultInstance()));
                    pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                    pipeline.addLast(new ProtobufEncoder());
                    pipeline.addLast(new ProtobufClientHandler());
                }
            });

            // 设置参数
            clientBootstrap.option(ChannelOption.SO_BACKLOG, 128);

            // 连接服务端
            ChannelFuture channelFuture = clientBootstrap.connect("127.0.0.1", 51001).sync();
            System.out.println("Netty4 Client start...");
            channelFuture.channel().closeFuture().sync();
        } finally {
            // 关闭资源
            workerGroup.shutdownGracefully();
        }
    }
}







