package morning.cat.netty.client.handle;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import morning.cat.protos.FacadeManager;

public class ProtobufClientHandler extends SimpleChannelInboundHandler<FacadeManager.Facade> {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush(FacadeManager.Facade.newBuilder().setDataType(FacadeManager.Facade.DataType.StudentType).setStudent(FacadeManager.Student.newBuilder().setId(100L).setName("学生A").build()).build());
        ctx.channel().writeAndFlush(FacadeManager.Facade.newBuilder().setDataType(FacadeManager.Facade.DataType.StudentType).setStudent(FacadeManager.Student.newBuilder().setId(101L).setName("学生B").build()).build());
        ctx.channel().writeAndFlush(FacadeManager.Facade.newBuilder().setDataType(FacadeManager.Facade.DataType.TeacherType).setTeacher(FacadeManager.Teacher.newBuilder().setId(201L).setName("李老师").setClassName("高一11班").build()));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FacadeManager.Facade msg) throws Exception {

        if (msg.getDataType().equals(FacadeManager.Facade.DataType.StudentType)) {
            FacadeManager.Student student = msg.getStudent();
            System.out.println("Server 收到消息【学生】：" + student.getId() + " " + student.getName());
        } else if (msg.getDataType().equals(FacadeManager.Facade.DataType.TeacherType)) {
            FacadeManager.Teacher teacher = msg.getTeacher();
            System.out.println("Server 收到消息【老师】：" + teacher.getId() + " " + teacher.getName() + " " + teacher.getClassName());
        }
    }
}

