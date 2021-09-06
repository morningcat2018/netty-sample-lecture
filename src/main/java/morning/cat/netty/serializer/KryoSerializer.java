package morning.cat.netty.serializer;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/9/7 上午1:50
 */
public class KryoSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.KRYO.getCode();
    }

    @Override
    public byte[] serialize(Object object) {
        return new byte[0];
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return null;
    }
}
