package morning.cat.netty.server.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @describe: 处理 TextWebSocketFrame
 * @author: morningcat.zhang
 * @date: 2021/8/7 下午5:06
 */
public class MyWebSocketHandle extends SimpleChannelInboundHandler<TextWebSocketFrame> { // WebSocketFrame 的一种


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("收到消息：" + msg.text());

        //
        TextWebSocketFrame res = new TextWebSocketFrame("Current Time:" + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        ctx.channel().writeAndFlush(res);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded " + ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved " + ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
