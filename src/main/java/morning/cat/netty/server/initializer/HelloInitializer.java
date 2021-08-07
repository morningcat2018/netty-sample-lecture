package morning.cat.netty.server.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import morning.cat.netty.server.handle.MyWebSocketHandle;

/**
 * @describe: 回调方法
 * @author: morningcat.zhang
 * @date: 2021/8/5 上午2:43
 */
public class HelloInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline channelPipeline = socketChannel.pipeline(); // 管道

        // idle handle
        channelPipeline.addLast(new HttpServerCodec()); // http
        channelPipeline.addLast(new ChunkedWriteHandler());
        channelPipeline.addLast(new HttpObjectAggregator(1024 * 8));

        /**
         * ws://host:port/context_path
         * http://host:port/context_path
         */
        channelPipeline.addLast(new WebSocketServerProtocolHandler("/ws")); // ws://localhost:51001/ws

        channelPipeline.addLast(new MyWebSocketHandle());
    }
}
