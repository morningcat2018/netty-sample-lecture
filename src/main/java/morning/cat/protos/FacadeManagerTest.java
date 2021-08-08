package morning.cat.protos;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/8/8 下午12:10
 */
public class FacadeManagerTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        // 序列化
        FacadeManager.Student stu = FacadeManager.Student.newBuilder().setId(100L).setName("张三").build();
        byte[] bytes = stu.toByteArray();

        // 反序列化
        FacadeManager.Student stu2 = FacadeManager.Student.parseFrom(bytes);
        System.out.println(stu2.getId());
        System.out.println(stu2.getName());
    }
}
