package morning.cat.netty.packet;

import morning.cat.netty.command.CommandEnum;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/9/8 上午1:21
 */
public class MessageResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return CommandEnum.MESSAGE_RESPONSE.getCode();
    }
}
