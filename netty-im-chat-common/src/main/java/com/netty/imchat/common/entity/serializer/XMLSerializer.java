package com.netty.imchat.common.entity.serializer;

import com.netty.imchat.common.enums.SerializerEnum;

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
        return SerializerEnum.XML.getCode();
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
