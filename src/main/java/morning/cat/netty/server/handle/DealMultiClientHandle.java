package morning.cat.netty.server.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/8/5 下午3:45
 */
public class DealMultiClientHandle extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        String name = ctx.channel().remoteAddress().toString();
        ctx.channel().writeAndFlush("欢迎登陆，" + name);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        String name = ctx.channel().remoteAddress().toString();
        System.out.println(name + ":登陆上线");

        group.writeAndFlush(name + " 已上线");
        group.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        String name = ctx.channel().remoteAddress().toString();
        System.out.println(name + ":下线");

        group.writeAndFlush(name + " 已下线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
}
