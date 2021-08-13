package morning.cat.netty.server.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @see ChannelOutboundHandlerAdapter,ChannelInboundHandlerAdapter
 */
public class MyChannelHandle extends SimpleChannelInboundHandler {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught");
        super.exceptionCaught(ctx, cause);
    }

    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

    }
}
