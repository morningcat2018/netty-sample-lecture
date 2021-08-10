package morning.cat.grpc.server;

import io.grpc.stub.StreamObserver;
import morning.cat.grpc.proto.GreeterGrpc;
import morning.cat.grpc.proto.HelloReply;
import morning.cat.grpc.proto.HelloRequest;

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
    public StreamObserver<HelloRequest> sayRequestStream(StreamObserver<HelloReply> responseObserver) {

        // ??
        responseObserver.onNext(HelloReply.newBuilder().setMessage("你好").build());
        responseObserver.onCompleted();

        return super.sayRequestStream(responseObserver);
    }
}
