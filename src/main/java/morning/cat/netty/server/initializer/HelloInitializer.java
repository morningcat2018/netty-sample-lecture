package morning.cat.netty.server.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import morning.cat.netty.server.handle.MyIdleHandle;

import java.util.concurrent.TimeUnit;

/**
 * @describe: 回调方法
 * @author: morningcat.zhang
 * @date: 2021/8/5 上午2:43
 */
public class HelloInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline channelPipeline = socketChannel.pipeline(); // 管道

        // idle handle
        channelPipeline.addLast(new IdleStateHandler(5, 7, 10, TimeUnit.SECONDS));
        channelPipeline.addLast(new MyIdleHandle());
    }
}
