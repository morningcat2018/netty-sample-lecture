package morning.cat.netty.packet;

import io.netty.buffer.ByteBuf;
import morning.cat.netty.command.CommandEnum;
import morning.cat.netty.exception.IMException;
import morning.cat.netty.serializer.Serializer;
import morning.cat.netty.serializer.SerializerAlgorithm;

import java.util.Objects;

/**
 * @describe: 编解码器
 * @author: morningcat.zhang
 * @date: 2021/9/7 上午1:53
 */
public class PacketCodeC {

    private PacketCodeC() {
    }

    public static final PacketCodeC INSTANCE = new PacketCodeC();

    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        return encode(byteBuf, packet, Serializer.DEFAULT);
    }

    public ByteBuf encode(ByteBuf byteBuf, Packet packet, Serializer serializer) {
        // 1. 创建 ByteBuf 对象
        //ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();

        // 2. 序列化 Java 对象
        byte[] bytes = serializer.serialize(packet);

        // 3. 实际编码过程
        byteBuf.writeInt(Packet.MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(serializer.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) {
        // magic number
        //byteBuf.skipBytes(4);
        int magicNumber = byteBuf.readInt();
        if (!Objects.equals(magicNumber, Packet.MAGIC_NUMBER)) {
            throw new IMException("数据包文件格式不符合规范");
        }

        // 跳过版本号
        //byteBuf.skipBytes(1);
        Byte version = byteBuf.readByte();

        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = CommandEnum.getClassByCode(command);
        Serializer serializer = SerializerAlgorithm.getSerializerByCode(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }

        throw new IMException("数据包解析失败");
    }

}
