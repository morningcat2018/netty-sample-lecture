package morning.cat.netty.server.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/8/7 下午5:06
 */
public class MyIdleHandle extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            String eventType = null;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    eventType = "读取空闲";
                    break;
                case WRITER_IDLE:
                    eventType = "写入空闲";
                    break;
                case ALL_IDLE:
                    eventType = "读写空闲";
                    break;
                default:
            }

            System.out.println(ctx.channel().remoteAddress() + " 超时事件:" + eventType);
            ctx.channel().closeFuture();
        }
    }
}
