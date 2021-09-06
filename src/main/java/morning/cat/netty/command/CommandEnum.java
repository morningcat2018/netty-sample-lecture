package morning.cat.netty.command;

import lombok.Getter;
import morning.cat.netty.packet.ErrorPacket;
import morning.cat.netty.packet.LoginRequestPacket;
import morning.cat.netty.packet.Packet;
import morning.cat.netty.serializer.Serializer;
import morning.cat.netty.serializer.SerializerAlgorithm;

import java.util.Objects;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/9/7 上午1:39
 */
@Getter
public enum CommandEnum {

    ERROR((byte) -1, "错误指令", ErrorPacket.class),
    LOGIN_REQUEST((byte) 1, "登陆请求", LoginRequestPacket.class),
    LOGIN_RESPONSE((byte) 2, "登陆响应", null), // TODO

    MESSAGE_REQUEST((byte) 11, "登陆请求", null),// TODO
    MESSAGE_RESPONSE((byte) 12, "登陆响应", null),// TODO
    ;

    private Byte code;
    private String desc;
    private Class<? extends Packet> packetClass;

    CommandEnum(Byte code, String desc, Class<? extends Packet> packetClass) {
        this.code = code;
        this.desc = desc;
        this.packetClass = packetClass;
    }

    public static Class<? extends Packet> getClassByCode(Byte code) {
        for (CommandEnum commandEnum : CommandEnum.values()) {
            if (Objects.equals(code, commandEnum.code)) {
                return commandEnum.packetClass;
            }
        }
        return ERROR.packetClass;
    }
}
