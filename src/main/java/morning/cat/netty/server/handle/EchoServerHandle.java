package morning.cat.netty.server.handle;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/4/23 3:41 PM
 * @see ChannelOutboundHandlerAdapter,ChannelInboundHandlerAdapter
 */
public class EchoServerHandle extends ChannelInboundHandlerAdapter {
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
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("channelRead ----->");
		if (msg instanceof ByteBuf) {
			ByteBuf byteBuf = (ByteBuf) msg;
			System.out.println(byteBuf.toString(Charset.defaultCharset()));

		}

		// ByteBuf byteBuf = ByteBufUtil.writeUtf8(ByteBufAllocator.DEFAULT,
		// "server已收到此消息\n");
		// ctx.write(byteBuf);
		// super.channelRead(ctx, msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelReadComplete");

//        Channel channel = ctx.channel();
//        channel.writeAndFlush();

		ctx.flush();
		// super.channelReadComplete(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("exceptionCaught");
		super.exceptionCaught(ctx, cause);
	}
}
