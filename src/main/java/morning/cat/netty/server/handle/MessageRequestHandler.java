package morning.cat.netty.server.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import morning.cat.netty.packet.MessageRequestPacket;
import morning.cat.netty.packet.MessageResponsePacket;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/9/8 上午1:28
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        MessageResponsePacket messageResponsePacket = receiveMessage(msg);
        ctx.channel().writeAndFlush(messageResponsePacket);
    }

    private MessageResponsePacket receiveMessage(MessageRequestPacket msg) {
        return null;// TODO
    }
}
