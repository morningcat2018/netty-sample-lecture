package morning.cat.grpc.server;

import io.grpc.stub.StreamObserver;
import morning.cat.grpc.proto.GreeterGrpc;
import morning.cat.grpc.proto.HelloReply;
import morning.cat.grpc.proto.HelloRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/8/11 上午12:37
 */
public class MyGreeterImpl extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
//        HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
//        responseObserver.onNext(reply);
        responseObserver.onNext(HelloReply.newBuilder().setMessage("你好 " + request.getName()).build());
        // onNext 不能调用多次
        responseObserver.onCompleted();
    }

    @Override
    public void sayHelloStream(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        responseObserver.onNext(HelloReply.newBuilder().setMessage("Hello " + request.getName()).build());
        responseObserver.onNext(HelloReply.newBuilder().setMessage("你好 " + request.getName()).build());
        // onNext 可以调用多次
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<HelloRequest> sayManyRequest(StreamObserver<HelloReply> responseObserver) {
        // 因为是流式请求，所以有一个监视者，来一个请求就处理一个入参
        return new StreamObserver<HelloRequest>() {

            List<String> reqList = new ArrayList<>();

            @Override
            public void onNext(HelloRequest request) {
                // 因为流式请求，入参会被调用多次，而响应不是流式，onNext 只能调用一次
                // responseObserver.onNext(HelloReply.newBuilder().setMessage("Hello " + request.getName()).build());

                reqList.add(request.getName());
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                // 流式请求结束后进行统一处理
                responseObserver.onNext(HelloReply.newBuilder().setMessage("Hello " + reqList.stream().collect(Collectors.joining(","))).build());
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<HelloRequest> sayManyToMany(StreamObserver<HelloReply> responseObserver) {
        return new StreamObserver<HelloRequest>() {
            @Override
            public void onNext(HelloRequest request) {
                // 因为流式请求，而响应也是流式，可以来一个处理一个
                responseObserver.onNext(HelloReply.newBuilder().setMessage("Hello " + request.getName()).build());
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
