package morning.cat.netty.client.handle;


import com.google.protobuf.GeneratedMessageV3;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import morning.cat.protos.StudentManager;

public class ProtobufClientHandler extends SimpleChannelInboundHandler<GeneratedMessageV3> {

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
    protected void channelRead0(ChannelHandlerContext ctx, GeneratedMessageV3 msg) throws Exception {
        if (msg instanceof StudentManager.Student) {
            StudentManager.Student student = (StudentManager.Student) msg;
            System.out.println("Client 收到消息：" + student.getId() + " " + student.getName());
        } else if (msg instanceof StudentManager.Teacher) {
            StudentManager.Teacher teacher = (StudentManager.Teacher) msg;
            System.out.println("Client 收到消息：" + teacher.getId() + " " + teacher.getName() + " " + teacher.getClassName());
        }
    }
}

