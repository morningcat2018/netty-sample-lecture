package morning.cat.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @describe: {@link java.nio.channels.Selector}
 * @author: morningcat.zhang
 * @date: 2021/8/12 下午10:02
 */
public class SelectorTest {

    public static void main(String[] args) throws IOException {
        int[] ports = new int[]{5000, 5001, 5002, 5003, 5004};
        Selector selector = Selector.open();

        for (int i = 0; i < ports.length; i++) {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket = serverSocketChannel.socket();
            serverSocket.bind(new InetSocketAddress(ports[i]));

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            // 将 serverSocketChannel 注册到 selector 上
            // 注册一个监听接收请求的事件

            System.out.println("监听端口：" + ports[i]);
        }

        while (true) {
            selector.select(); // 阻塞操作

            // 当有事件触发时
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey selectionKey = keyIterator.next();

                if (selectionKey.isAcceptable()) {
                    // 请求事件
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    keyIterator.remove(); // 注意：处理完后移除该 selectionKey

                    System.out.println("获取客户端连接：" + socketChannel.getRemoteAddress());
                } else if (selectionKey.isReadable()) {
                    // 可读事件
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    readChannel(socketChannel);
                    keyIterator.remove(); // 注意：处理完后移除该 selectionKey
                }

                //
            }
        }

    }

    private static void readChannel(SocketChannel socketChannel) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        System.out.print(socketChannel.getRemoteAddress() + ":");
        while (true) {
            int c = socketChannel.read(byteBuffer);
            if (c < 1) {
                break;
            }
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                System.out.print((char) byteBuffer.get());
            }

            byteBuffer.flip();
            socketChannel.write(byteBuffer);

            byteBuffer.clear(); // 这一步很重要，不执行 clear 会无限循环下去，因为 c 到后面一直为 0
        }
        //System.out.println();
    }
}
