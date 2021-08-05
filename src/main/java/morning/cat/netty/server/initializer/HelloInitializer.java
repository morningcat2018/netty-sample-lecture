package morning.cat.netty.server.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import morning.cat.netty.server.handle.HelloHttpServerHandle;

/**
 * @describe: 回调方法
 * @author: morningcat.zhang
 * @date: 2021/8/5 上午2:43
 */
public class HelloInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline channelPipeline = socketChannel.pipeline(); // 管道

        channelPipeline.addLast("http", new HttpServerCodec());
        channelPipeline.addLast("helloHttpServerHandle", new HelloHttpServerHandle());
    }
}
