package morning.cat.nio;

import java.nio.ByteBuffer;

/**
 * {@link java.nio.ByteBuffer}
 */
public class ByteBufferTest2 {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);

        // 只读Buffer，不能执行写入操作
        ByteBuffer byteBufferRead = byteBuffer.asReadOnlyBuffer();
        //byteBufferRead.putChar('国'); // java.nio.ReadOnlyBufferException

        // 直接buffer : 分配内存到堆外，和外部IO设备通信可以实现零拷贝（省去了从JVM管理的内存到系统的直接内存的拷贝过程）
        // 也不会被垃圾回归直接管理，只有当指向其的引用Buffer回收时，其 Buffer 上的 long address 变量不再引用外部直接内存时才会被回收
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(10);

        // Used only by direct buffers
        // NOTE: hoisted here for speed in JNI GetDirectBufferAddress
        // long address;

        directBuffer.put((byte) 1);
        directBuffer.flip();
        System.out.println(directBuffer.get());

    }
}
