package morning.cat.netty.client.handle;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import morning.cat.protos.StudentManager;

public class ProtobufClientHandler extends SimpleChannelInboundHandler<StudentManager.Student> {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush(StudentManager.Student.newBuilder().setId(100L).setName("张三"));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, StudentManager.Student msg) throws Exception {
        System.out.println("收到消息：" + msg.getId() + " " + msg.getName());
    }
}

