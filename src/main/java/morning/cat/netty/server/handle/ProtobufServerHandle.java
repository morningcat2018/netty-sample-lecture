package morning.cat.netty.server.handle;

import com.google.protobuf.GeneratedMessageV3;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import morning.cat.protos.StudentManager;

public class ProtobufServerHandle extends SimpleChannelInboundHandler<GeneratedMessageV3> {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GeneratedMessageV3 msg) throws Exception {
        if (msg instanceof StudentManager.Student) {
            StudentManager.Student student = (StudentManager.Student) msg;
            System.out.println("Server 收到消息：" + student.getId() + " " + student.getName());
        } else if (msg instanceof StudentManager.Teacher) {
            StudentManager.Teacher teacher = (StudentManager.Teacher) msg;
            System.out.println("Server 收到消息：" + teacher.getId() + " " + teacher.getName() + " " + teacher.getClassName());
        }

        ctx.channel().writeAndFlush(StudentManager.Teacher.newBuilder().setId(200L).setName("张三丰").setClassName("高二理五").build());

    }
}
