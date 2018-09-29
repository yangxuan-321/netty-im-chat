package studt.imchat.protocol.entity.serializer;

/**
 * @author Kevin
 * @Title: XMLSerializer
 * @ProjectName studyjava
 * @Description: TODO 序列化 -- XML实现[暂未实现]
 * @date 2018/9/29 20:08
 */
public class XMLSerializer implements Serializer {
    @Override
    public Byte getSerializerAlgorithm() {
        return null;
    }

    @Override
    public <T> byte[] serialize(T t) {
        return new byte[0];
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return null;
    }
}
