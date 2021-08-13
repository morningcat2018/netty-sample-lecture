package morning.cat.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * @describe: {https://crunchify.com/java-nio-non-blocking-io-with-server-client-example-java-nio-bytebuffer-and-channels-selector-java-nio-vs-io/}
 * @author: morningcat.zhang
 * @date: 2021/8/13 下午9:43
 */
public class NioClient {
    public static void main(String[] args) throws IOException, InterruptedException {

        InetSocketAddress crunchifyAddr = new InetSocketAddress("localhost", 5001);
        SocketChannel crunchifyClient = SocketChannel.open(crunchifyAddr);
        System.out.println("Connecting to Server on port 5001...");

        ArrayList<String> companyDetails = new ArrayList<String>();
        companyDetails.add("Facebook");
        companyDetails.add("Twitter");
        companyDetails.add("IBM");
        companyDetails.add("Google");
        companyDetails.add("Huawei");

        for (String companyName : companyDetails) {
            ByteBuffer buffer = ByteBuffer.wrap(companyName.getBytes());
            crunchifyClient.write(buffer);
            System.out.println("sending: " + companyName);
            buffer.clear();
            Thread.sleep(1000);
        }
        // close(): Closes this channel.
        // If the channel has already been closed then this method returns immediately.
        // Otherwise it marks the channel as closed and then invokes the implCloseChannel method in order to complete the close operation.
        crunchifyClient.close();
    }
}
