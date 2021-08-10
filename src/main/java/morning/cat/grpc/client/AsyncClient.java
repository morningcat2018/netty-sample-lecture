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
import io.grpc.stub.StreamObserver;
import morning.cat.grpc.proto.GreeterGrpc;
import morning.cat.grpc.proto.HelloReply;
import morning.cat.grpc.proto.HelloRequest;

/**
 * 只要客户端是以流式的方式发送请求，都是需要异步进行的
 */
public class AsyncClient {

    /**
     * 非阻塞的客户端
     */
    private final GreeterGrpc.GreeterStub stub;

    public AsyncClient(Channel channel) {
        stub = GreeterGrpc.newStub(channel);
    }

    private void sayManyRequest() {

        // 异步响应
        StreamObserver<HelloReply> responseObserver = new StreamObserver<HelloReply>() {
            @Override
            public void onNext(HelloReply reply) {
                System.out.println("client:" + reply.getMessage());
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }
        };

        StreamObserver<HelloRequest> requestStreamObserver = this.stub.sayManyRequest(responseObserver);
        // 因为是流式入参，所以此处的 onNext 可以多个
        requestStreamObserver.onNext(HelloRequest.newBuilder().setName("张三").build());
        requestStreamObserver.onNext(HelloRequest.newBuilder().setName("李四").build());
        requestStreamObserver.onNext(HelloRequest.newBuilder().setName("王武").build());
        requestStreamObserver.onNext(HelloRequest.newBuilder().setName("赵流").build());
        requestStreamObserver.onCompleted();

    }

    public static void main(String[] args) throws Exception {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:50051").usePlaintext().build();
        try {
            AsyncClient client = new AsyncClient(channel);
            client.sayManyRequest();
            Thread.sleep(1000); // 因为异步调用，所以需要等待一段时间
        } finally {
            channel.shutdown();
        }
    }
}
