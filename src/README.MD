
P12 ～ P17

1. 基本概念

rmi : remote method invocation 远程方法调用（java）

rpc : remote procedure call    远程过程调用（只是一个概念，有很多实现：gRPC,Dubbo,thrift）

---

client : stub
server : skeleton

2. Protobuf

https://developers.google.com/protocol-buffers/

---

下载 

https://github.com/protocolbuffers/protobuf/releases

注意：下载 protoc- 开头的文件

配置环境变量[本机 MacxOS11.5]

```
vim .bash_profile
#添加：
export PATH=$PATH:/xxx/protobuf/bin/
#生效
source .bash_profile

#验证
protoc --version
```
参考[ protoc 的安装](https://blog.csdn.net/sszzyzzy/article/details/89946075)

---

3. Protobuf 教程 Java

https://developers.google.com/protocol-buffers/docs/javatutorial

4. code

https://github.com/morningcat2018/netty-sample-lecture/tree/v1.1-p12-Protobuf

