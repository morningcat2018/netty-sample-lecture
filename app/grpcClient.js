var PROTO_FILE_PATH = 'src/main/proto/hello.proto';
var grpc = require("grpc");
var grpcServer = grpc.load(PROTO_FILE_PATH).helloworld;

var client = new grpcServer.Greeter('localhost:50051', grpc.credentials.createInsecure());

client.SayHello({name: "张三"}, function (err, res) {
    console.log(res.message);
});