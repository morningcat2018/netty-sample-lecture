package morning.cat.netty.server.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import morning.cat.netty.packet.LoginRequestPacket;
import morning.cat.netty.packet.LoginResponsePacket;
import morning.cat.netty.utils.Session;
import morning.cat.netty.utils.SessionUtil;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/9/8 上午1:28
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginResponsePacket = login(ctx, loginRequestPacket);
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private LoginResponsePacket login(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        String userId = SessionUtil.randomUserId();
        loginResponsePacket.setUserId(userId);
        SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUserName()), ctx.channel());
        return loginResponsePacket;
    }

    // 用户断线之后取消绑定
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
    }
}
