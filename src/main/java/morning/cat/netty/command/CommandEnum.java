package morning.cat.netty.command;

import lombok.Getter;
import morning.cat.netty.packet.*;

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
    LOGIN_RESPONSE((byte) 2, "登陆响应", LoginResponsePacket.class),

    MESSAGE_REQUEST((byte) 11, "发送消息", MessageRequestPacket.class),
    MESSAGE_RESPONSE((byte) 12, "消息响应", MessageResponsePacket.class),
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
