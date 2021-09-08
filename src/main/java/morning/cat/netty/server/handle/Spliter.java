package morning.cat.netty.server.handle;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import morning.cat.netty.packet.Packet;

/**
 * @describe: 拆包器
 * @author: morningcat.zhang
 * @date: 2021/9/8 上午1:36
 */
public class Spliter extends LengthFieldBasedFrameDecoder {


    /**
     * -------------------------------------------------------------------------------------
     * |    魔法数   | 协议版本 | 序列化协议 ｜   指令  |     数据长度    |       数据           |
     * | 0xABCD1234 |  Ox01   |   0x01    |   0x0A  |   0x0000000C  |    "HELLO,WORLD"     |
     * -------------------------------------------------------------------------------------
     *
     * @see LengthFieldBasedFrameDecoder
     */
    private static final int LENGTH_FIELD_OFFSET = 7;  // 长度域偏移量 （数据域起始位置）
    private static final int LENGTH_FIELD_LENGTH = 4;  // 长度域长度   （数据域长度）

    public Spliter() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH, 0, 0);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        // 屏蔽非本协议的客户端
        if (in.getInt(in.readerIndex()) != Packet.MAGIC_NUMBER) {
            ctx.channel().close();
            return null;
        }

        return super.decode(ctx, in);
    }
}
