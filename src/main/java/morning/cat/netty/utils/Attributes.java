package morning.cat.netty.utils;

import io.netty.util.AttributeKey;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/9/9 上午2:11
 */
public class Attributes {

    public static final AttributeKey<Session> SESSION = AttributeKey.newInstance("session");


    public static final AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
    ;
}
