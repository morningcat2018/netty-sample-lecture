package morning.cat.netty.server.handle;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;


/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/8/5 上午2:50
 */
public class HelloHttpServerHandle extends SimpleChannelInboundHandler<HttpObject> {

    protected void channelRead0(ChannelHandlerContext context, HttpObject msg) throws Exception {
        if (!(msg instanceof HttpRequest)) {
            System.out.println("not HttpRequest");
            return;
        }
        ByteBuf byteBuf = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);

        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());
        context.writeAndFlush(response);
    }
}
