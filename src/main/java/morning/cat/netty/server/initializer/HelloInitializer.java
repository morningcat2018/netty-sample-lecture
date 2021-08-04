package morning.cat.netty.server.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import morning.cat.netty.server.handle.EchoServerHandle;
import morning.cat.netty.server.handle.HelloHttpServerHandle;

/**
 * @describe: 回调方法
 * @author: morningcat.zhang
 * @date: 2021/8/5 上午2:43
 */
public class HelloInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline channelPipeline = socketChannel.pipeline(); // 管道
        // 接收信息转换成string(上行)
        // channelPipeline.addLast("StringDecoder", new StringDecoder());
        // 回写直接写入字符串
        // channelPipeline.addLast("StringEncoder", new StringEncoder());

        //
        // channelPipeline.addLast("EchoServerHandle", new EchoServerHandle());
        //
        // channelPipeline.addLast("HelloHandle", new HelloHandle());

        //
        //channelPipeline.addLast("HelloHandle", new EchoServerHandle());

        // channelPipeline.addLast("TimeServerHandler", new TimeServerHandler());

        channelPipeline.addLast("http", new HttpServerCodec());
        channelPipeline.addLast("helloHttpServerHandle", new HelloHttpServerHandle());
    }
}
