package morning.cat.netty.server.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import morning.cat.protos.FacadeManager;

public class ProtobufServerHandle extends SimpleChannelInboundHandler<FacadeManager.Facade> {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
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

        FacadeManager.Teacher teacherA = FacadeManager.Teacher.newBuilder().setId(200L).setName("张三丰").setClassName("高二理五").build();
        ctx.channel().writeAndFlush(FacadeManager.Facade.newBuilder().setDataType(FacadeManager.Facade.DataType.TeacherType).setTeacher(teacherA).build());
    }
}
