package morning.cat.netty.utils;

import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/9/9 上午2:23
 */
public class LoginUtil {

    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);

        return loginAttr.get() != null;
    }
}
