package morning.cat.netty.server.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import morning.cat.protos.StudentManager;

public class ProtobufServerHandle extends SimpleChannelInboundHandler<StudentManager.Student> {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, StudentManager.Student msg) throws Exception {
        System.out.println("Server 收到消息：" + msg.getId() + " " + msg.getName());
        ctx.channel().writeAndFlush(StudentManager.Student.newBuilder().setId(101L).setName("李四"));
    }
}
