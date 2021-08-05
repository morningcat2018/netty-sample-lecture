package morning.cat.netty.server.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/8/5 下午3:45
 */
public class SocketHandle extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        // get
        System.out.println(ctx.channel().remoteAddress() + ":" + msg);

        //
        ctx.channel().writeAndFlush("from server say ok");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
}
