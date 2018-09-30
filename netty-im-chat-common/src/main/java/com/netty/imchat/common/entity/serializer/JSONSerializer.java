package com.netty.imchat.common.entity.serializer;

import com.alibaba.fastjson.JSON;
import com.netty.imchat.common.enums.SerializerEnum;


/**
 * @author Kevin
 * @Title: JSONSerializer
 * @ProjectName studyjava
 * @Description: TODO  序列化对象的JSON实现方式
 * @date 2018/9/29 19:51
 */
public class JSONSerializer implements Serializer {

    @Override
    public Byte getSerializerAlgorithm() {
        return SerializerEnum.JSON.getCode();
    }

    @Override
    public <T> byte[] serialize(T object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}