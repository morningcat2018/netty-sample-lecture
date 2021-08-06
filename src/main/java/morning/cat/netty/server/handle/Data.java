package morning.cat.netty.server.handle;

import io.netty.channel.ChannelHandlerContext;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/8/6 下午5:09
 */
public class Data {

    public static Map<String, ChannelHandlerContext> map = new ConcurrentHashMap<>();

    public static List<ChannelHandlerContext> list = new CopyOnWriteArrayList<>();
}
