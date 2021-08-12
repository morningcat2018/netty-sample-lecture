package morning.cat.nio;

import java.nio.ByteBuffer;

/**
 * @describe: ByteBuffer
 * @author: morningcat.zhang
 * @date: 2021/8/11 下午10:41
 */
public class ByteBufferTest {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        byteBuffer.putChar('国');
        byteBuffer.putInt(1314);
        byteBuffer.putLong(123456789L);
        byteBuffer.putDouble(3.141592654);
        byteBuffer.putShort((short) 3);

        byteBuffer.flip();
        // 必须依次取出
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getDouble());
        System.out.println(byteBuffer.getShort());
    }
}
