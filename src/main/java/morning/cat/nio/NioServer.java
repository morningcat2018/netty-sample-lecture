package morning.cat.nio;


import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class NioServer {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(5001));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("监听端口：" + 5001);

        while (true) {
            selector.select(); // 阻塞操作

            // 当有事件触发时
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            for (SelectionKey selectionKey : selectionKeySet) {
                if (selectionKey.isAcceptable()) {
                    // 请求事件
                    ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = ssc.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("获取客户端连接：" + socketChannel.getRemoteAddress());
                } else if (selectionKey.isReadable()) {
                    // 可读事件
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    readChannel(socketChannel);
                }
            }
            selectionKeySet.clear();

        }

    }

    private static void readChannel(SocketChannel socketChannel) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        System.out.print(socketChannel.getRemoteAddress() + ":");
        List<Byte> byteList = new LinkedList<>();
        while (true) {
            int c = socketChannel.read(byteBuffer);
            if (c < 1) {
                break;
            }
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                byteList.add(byteBuffer.get());
            }

            byteBuffer.flip();
            socketChannel.write(byteBuffer);

            byteBuffer.clear();
        }
        Byte[] bytess = byteList.toArray(new Byte[byteList.size()]);
        byte[] bytes = ArrayUtils.toPrimitive(bytess);
        String result = new String(bytes);
        System.out.println(result);
        if (Objects.equals(result, "Huawei")) {
            socketChannel.close();
        }
    }
}
