package morning.cat.netty.packet;

import lombok.Data;
import morning.cat.netty.command.CommandEnum;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/9/7 上午1:42
 */
@Data
public class LoginRequestPacket extends Packet {

    //private Integer userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return CommandEnum.LOGIN_REQUEST.getCode();
    }
}
