package morning.cat.netty.server.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/8/5 下午3:45
 */
public class DealMultiClientHandle extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        String name = ctx.channel().remoteAddress().toString();
        ctx.channel().writeAndFlush("欢迎登陆，" + name);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        String name = ctx.channel().remoteAddress().toString();
        System.out.println(name + ":登陆上线");
        Data.list.forEach(context -> context.channel().writeAndFlush(name + " 已上线"));
        Data.list.add(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        Data.list.remove(ctx);
        String name = ctx.channel().remoteAddress().toString();
        Data.list.forEach(context -> context.channel().writeAndFlush(name + " 已下线"));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
}
