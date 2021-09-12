package morning.cat.netty.server.handle;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.io.IOException;
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
        HttpMethod method = httpRequest.method(); // GET POST PUT DELETE

        URI uri = new URI(httpRequest.uri());
        if (Objects.equals(uri.getPath(), "/favicon.ico")) {
            return;
        }
        System.out.println(method.name() + " " + httpRequest.uri());
        System.out.println(uri.getPath());
        System.out.println(uri.getQuery());

        if (httpRequest instanceof FullHttpRequest) {
            getJobType((FullHttpRequest) httpRequest);
        }


        ByteBuf byteBuf = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());
        context.writeAndFlush(response);
    }

    //parse job type 0,1
    private String getJobType(FullHttpRequest request) throws IOException {
        ByteBuf jsonBuf = request.content();
        String jsonStr = jsonBuf.toString(CharsetUtil.UTF_8);
        System.out.println(jsonStr);
        return jsonStr;
    }

}
