var PROTO_FILE_PATH = 'src/main/proto/hello.proto';
var grpc = require("grpc");
var grpcServer = grpc.load(PROTO_FILE_PATH).helloworld;

var server = new grpc.Server();
server.addService(grpcServer.Greeter.service, {
    SayHello: SayHello
});
server.bind('localhost:50051', grpc.ServerCredentials.createInsecure());
server.start();

function SayHello(call, callback) {
    callback(null, {message: 'HelloNodeJs ' + call.request.name});
}