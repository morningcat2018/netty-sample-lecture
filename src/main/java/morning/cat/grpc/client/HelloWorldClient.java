/*
 * Copyright 2015 The gRPC Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package morning.cat.grpc.client;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import morning.cat.grpc.proto.GreeterGrpc;
import morning.cat.grpc.proto.HelloReply;
import morning.cat.grpc.proto.HelloRequest;
import morning.cat.grpc.server.HelloWorldServer;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * A simple client that requests a greeting from the {@link HelloWorldServer}.
 */
public class HelloWorldClient {
    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    /**
     * Construct client for accessing HelloWorld server using the existing channel.
     */
    public HelloWorldClient(Channel channel) {
        // 'channel' here is a Channel, not a ManagedChannel, so it is not this code's responsibility to
        // shut it down.

        // Passing Channels to code makes code easier to test and makes it easier to reuse Channels.
        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    private void sayHello() {
        HelloRequest request = HelloRequest.newBuilder().setName("我的朋友").build();
        HelloReply response;
        try {
            response = this.blockingStub.sayHello(request);
        } catch (StatusRuntimeException e) {
            //logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            e.printStackTrace();
            return;
        }
        System.out.println("sayHello: " + response.getMessage());
    }

    private void sayHelloStream() {
        HelloRequest request = HelloRequest.newBuilder().setName("我的朋友").build();
        Iterator<HelloReply> replyIterator;
        try {
            replyIterator = this.blockingStub.sayHelloStream(request);
        } catch (StatusRuntimeException e) {
            //logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            e.printStackTrace();
            return;
        }

        while (replyIterator.hasNext()) {
            System.out.println("sayHelloStream: " + replyIterator.next().getMessage());
        }

    }

    /**
     * Greet server. If provided, the first element of {@code args} is the name to use in the
     * greeting. The second argument is the target server.
     */
    public static void main(String[] args) throws Exception {
        // Access a service running on the local machine on port 50051
        String target = "localhost:50051";

        // Create a communication channel to the server, known as a Channel. Channels are thread-safe
        // and reusable. It is common to create channels at the beginning of your application and reuse
        // them until the application shuts down.
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext()
                .build();
        try {
            HelloWorldClient client = new HelloWorldClient(channel);
            client.sayHelloStream();

        } finally {
            // ManagedChannels use resources like threads and TCP connections. To prevent leaking these
            // resources the channel should be shut down when it will no longer be used. If it may be used
            // again leave it running.
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}
