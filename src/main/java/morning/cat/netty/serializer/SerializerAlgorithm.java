package morning.cat.netty.serializer;

import lombok.Getter;

import java.util.Objects;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2021/9/7 上午1:45
 */
@Getter
public enum SerializerAlgorithm {

    FAST_JSON((byte) 1, "alibaba.fastjson", new JSONSerializer()),
    KRYO((byte) 2, "esotericsoftware.kryo", new KryoSerializer()),
    ;

    private Byte code;
    private String desc;
    private Serializer serializer;

    SerializerAlgorithm(Byte code, String desc, Serializer serializer) {
        this.code = code;
        this.desc = desc;
        this.serializer = serializer;
    }

    public static Serializer getSerializerByCode(Byte code) {
        for (SerializerAlgorithm s : SerializerAlgorithm.values()) {
            if (Objects.equals(code, s.code)) {
                return s.serializer;
            }
        }
        return FAST_JSON.serializer;
    }

}
