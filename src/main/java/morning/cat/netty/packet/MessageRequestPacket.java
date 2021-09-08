package morning.cat.netty.packet;

import lombok.Data;
import morning.cat.netty.command.CommandEnum;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/9/8 上午1:21
 */
@Data
public class MessageRequestPacket extends Packet {

    private String toUserId;
    private String message;

    @Override
    public Byte getCommand() {
        return CommandEnum.MESSAGE_REQUEST.getCode();
    }
}
