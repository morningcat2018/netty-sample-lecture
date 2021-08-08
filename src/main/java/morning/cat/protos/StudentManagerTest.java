package morning.cat.protos;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/8/8 下午12:10
 */
public class StudentManagerTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        // 序列化
        StudentManager.Student stu = StudentManager.Student.newBuilder().setId(100L).setName("张三").build();
        byte[] bytes = stu.toByteArray();

        // 反序列化
        StudentManager.Student stu2 = StudentManager.Student.parseFrom(bytes);
        System.out.println(stu2.getId());
        System.out.println(stu2.getName());
    }
}
