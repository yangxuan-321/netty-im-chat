package com.netty.imchat.common.entity.serializer;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import com.netty.imchat.common.enums.SerializerEnum;

/**
 * @author Kevin
 * @Title: ProtostuffSerializer
 * @ProjectName studyjava
 * @Description: TODO 使用Google开源的序列化开源方案 比JDK的要快得多
 * @date 2018/9/29 19:56
 */
public class ProtostuffSerializer implements Serializer {
    @Override
    public Byte getSerializerAlgorithm() {
        return SerializerEnum.PROTOSTUFF.getCode();
    }

    @Override
    public <T> byte[] serialize(T object) {
        Schema schema = RuntimeSchema.getSchema(object.getClass());
        return ProtobufIOUtil.toByteArray(object, schema, LinkedBuffer.allocate(256));
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        T obj = null;
        try {
            obj = clazz.newInstance();
            Schema schema = RuntimeSchema.getSchema(obj.getClass());
            ProtostuffIOUtil.mergeFrom(bytes, obj, schema);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
