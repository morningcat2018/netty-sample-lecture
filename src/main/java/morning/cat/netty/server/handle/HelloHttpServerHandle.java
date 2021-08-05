package morning.cat.netty.server.handle;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;
import java.util.Objects;


/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/8/5 上午2:50
 */
public class HelloHttpServerHandle extends SimpleChannelInboundHandler<HttpObject> {

    protected void channelRead0(ChannelHandlerContext context, HttpObject msg) throws Exception {
        if (!(msg instanceof HttpRequest)) {
            //System.out.println("not HttpRequest");
            return;
        }

        HttpRequest httpRequest = (HttpRequest) msg;
        String methodName = httpRequest.method().name();
        System.out.println(methodName + " " + httpRequest.uri()); // GET

        URI uri = new URI(httpRequest.uri());
        if (Objects.equals(uri.getPath(), "/favicon.ico")) {
            //System.out.println("不执行");
            return;
        }
        System.out.println(uri.getPath());
        System.out.println(uri.getQuery());


        ByteBuf byteBuf = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);

        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());
        context.writeAndFlush(response);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelUnregistered");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive");
        super.channelInactive(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded");
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved");
        super.handlerRemoved(ctx);
    }
}
