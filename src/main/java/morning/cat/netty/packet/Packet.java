package morning.cat.netty.packet;

import lombok.Data;

/**
 * @describe: 数据包基类
 * @author: morningcat.zhang
 * @date: 2021/9/7 上午1:37
 */
@Data
public abstract class Packet {

    public static final int MAGIC_NUMBER = 0xBABE1995;

    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 指令
     */
    public abstract Byte getCommand();
}
