package com.netty.imchat.common.enums;

import com.netty.imchat.common.entity.packet.ConnectResponsePacket;
import com.netty.imchat.common.entity.packet.Packet;
import com.netty.imchat.common.entity.packet.LoginRequestPacket;

/**
 * @author Kevin
 * @Title: CommandeEum
 * @ProjectName studyjava
 * @Description: TODO 命令枚举
 * @date 2018/9/29 19:01
 */
public enum CommandeEnum{
    LOGIN_REQUEST((byte)1, LoginRequestPacket.class),          //登录请求
    CONNECT_RESPONSE((byte)2, ConnectResponsePacket.class);

    //CODE
    private Byte code;
    //类
    private Class<? extends Packet> clazz;

    public Byte getCode() {
        return code;
    }

    public void setCode(Byte code) {
        this.code = code;
    }

    public Class<? extends Packet> getClazz() {
        return clazz;
    }

    public void setClazz(Class<? extends Packet> clazz) {
        this.clazz = clazz;
    }

    CommandeEnum(Byte code, Class<? extends Packet> clazz) {
        this.code = code;
        this.clazz = clazz;
    }

}
