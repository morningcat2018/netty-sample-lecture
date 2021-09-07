package morning.cat.netty.server.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import morning.cat.netty.packet.LoginRequestPacket;
import morning.cat.netty.packet.LoginResponsePacket;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/9/8 上午1:28
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginResponsePacket = login(loginRequestPacket);
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private LoginResponsePacket login(LoginRequestPacket loginRequestPacket) {
        return null; // TODO
    }
}
