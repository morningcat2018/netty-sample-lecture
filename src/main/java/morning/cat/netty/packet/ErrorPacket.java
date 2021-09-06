package morning.cat.netty.packet;

import lombok.Data;
import morning.cat.netty.command.CommandEnum;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/9/7 上午1:59
 */
@Data
public class ErrorPacket extends Packet {

    private String errorMessage;

    @Override
    public Byte getCommand() {
        return CommandEnum.ERROR.getCode();
    }
}
