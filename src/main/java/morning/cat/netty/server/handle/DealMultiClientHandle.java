package morning.cat.netty.server.handle;

import io.netty.channel.Channel;
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
        Channel channel = ctx.channel();

        group.forEach(channel1 -> {
            if (channel1 != channel) {
                channel1.writeAndFlush(channel.remoteAddress() + " 发送消息：" + msg);
            } else {
                channel.writeAndFlush("[onwer]:消息发送成功");
            }
        });
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        String name = channel.remoteAddress().toString();
        System.out.println(name + ":登陆上线");
        channel.writeAndFlush("欢迎登陆，" + name);

        group.writeAndFlush(name + ":已上线");
        group.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        String name = channel.remoteAddress().toString();
        System.out.println(name + ":下线");

        // 不用手动 remove ，会自动下线 channel
        group.writeAndFlush(name + ":已下线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
