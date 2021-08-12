package morning.cat.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @describe: {@link java.nio.channels.FileChannel}
 * @author: morningcat.zhang
 * @date: 2021/8/11 下午9:13
 */
public class FileChannelTest {
    public static void main(String[] args) throws Exception {

        FileInputStream inputStream = new FileInputStream("input.txt");
        FileChannel fileChannel = inputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        while (true) {
            int c = fileChannel.read(byteBuffer);
            //System.out.println("bytes count = " + c);
            if (c == -1) {
                break;
            }
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                System.out.print((char) byteBuffer.get());
            }
            byteBuffer.clear(); // 这一步很重要，不执行 clear 会无限循环下去，因为 c 到后面一直为 0
        }
    }
}
