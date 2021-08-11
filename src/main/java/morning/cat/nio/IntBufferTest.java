package morning.cat.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @describe: IntBuffer
 * @author: morningcat.zhang
 * @date: 2021/8/11 下午9:00
 */
public class IntBufferTest {

    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(10); // position = 0; limit = 10;  capacity = 10;
        printBuffer(intBuffer);

        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 5; i++) {
            intBuffer.put(random.nextInt(100)); // position = i+1; limit = 10;  capacity = 10;
            printBuffer(intBuffer);
        }
        intBuffer.flip(); // position = 0; limit = 5;  capacity = 10;
        //intBuffer.clear();
        printBuffer(intBuffer);

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get()); // position = i+1; limit = 10;  capacity = 10;
            printBuffer(intBuffer);
        }

        intBuffer.flip(); // position = 0; limit = 5;  capacity = 10;
        //intBuffer.clear(); // position = 0; limit = 10;  capacity = 10;
        printBuffer(intBuffer);

        // flip 会报错 java.nio.BufferOverflowException
        // clear 可行
        for (int i = 0; i < 10; i++) {
            intBuffer.put(random.nextInt(100)); // position = i+1; limit = 10;  capacity = 10;
            printBuffer(intBuffer);
        }
    }

    private static void printBuffer(IntBuffer intBuffer) {
        System.out.printf("position = %d; limit = %d;  capacity = %d\n", intBuffer.position(), intBuffer.limit(), intBuffer.capacity());
    }

}
