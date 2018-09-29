package studt.imchat.protocol.entity.serializer;

/**
 * @author Kevin
 * @Title: Serializer
 * @ProjectName studyjava
 * @Description: TODO 序列化接口
 * @date 2018/9/29 19:36
 */
public interface Serializer {
    /**
     * 算法 选择
     * @return
     */
    Byte getSerializerAlgorithm();

    /**
     * java 对象转换成二进制
     */
    <T> byte[] serialize(T t);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
