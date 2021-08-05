package morning.cat.netty.client.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import morning.cat.netty.client.handle.ClientHandle;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/8/5 下午4:08
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline channelPipeline = socketChannel.pipeline(); // 管道

        channelPipeline.addLast("Decoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,
                0, 4, 0, 4));
        channelPipeline.addLast("Encoder", new LengthFieldPrepender(4));
        channelPipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        channelPipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));

        channelPipeline.addLast(new ClientHandle());
    }
}