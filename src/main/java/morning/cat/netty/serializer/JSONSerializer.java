package morning.cat.netty.serializer;

import com.alibaba.fastjson.JSON;

/**
 * @describe: JSON序列化器
 * @author: morningcat.zhang
 * @date: 2021/9/7 上午1:48
 */
public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.FAST_JSON.getCode();
    }

    @Override
    public byte[] serialize(Object object) {

        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        return JSON.parseObject(bytes, clazz);
    }
}
