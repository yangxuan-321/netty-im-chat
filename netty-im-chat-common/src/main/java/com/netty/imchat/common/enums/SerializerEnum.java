package com.netty.imchat.common.enums;

import com.netty.imchat.common.entity.serializer.ProtostuffSerializer;
import com.netty.imchat.common.entity.serializer.Serializer;
import com.netty.imchat.common.entity.serializer.XMLSerializer;
import com.netty.imchat.common.entity.serializer.JSONSerializer;

/**
 * @author Kevin
 * @Title: Serializer
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/29 19:48
 */
public enum SerializerEnum {
    JSON((byte)1, JSONSerializer.class),            //JSON序列化方式
    PROTOSTUFF((byte)2, ProtostuffSerializer.class),
    XML((byte)3, XMLSerializer.class);

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
