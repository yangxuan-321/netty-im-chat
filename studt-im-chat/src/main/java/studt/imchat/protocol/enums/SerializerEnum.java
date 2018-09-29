package studt.imchat.protocol.enums;

import studt.imchat.protocol.entity.serializer.JSONSerializer;
import studt.imchat.protocol.entity.serializer.ProtostuffSerializer;
import studt.imchat.protocol.entity.serializer.Serializer;
import studt.imchat.protocol.entity.serializer.XMLSerializer;

/**
 * @author Kevin
 * @Title: Serializer
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/29 19:48
 */
public enum SerializerEnum {
    JSON((byte)1, JSONSerializer.class),            //JSON序列化方式
    PROTOSTUFF((byte)1, ProtostuffSerializer.class),
    XML((byte)1, XMLSerializer.class);

    //CODE
    private Byte code;
    //类
    private Class<? extends Serializer> clazz;

    public Byte getCode() {
        return code;
    }

    public void setCode(Byte code) {
        this.code = code;
    }

    public Class<? extends Serializer> getClazz() {
        return clazz;
    }

    public void setClazz(Class<? extends Serializer> clazz) {
        this.clazz = clazz;
    }

    SerializerEnum(Byte code, Class<? extends Serializer> clazz) {
        this.code = code;
        this.clazz = clazz;
    }
}
