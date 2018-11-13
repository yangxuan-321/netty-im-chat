package com.netty.imchat.common.enums;

import com.netty.imchat.common.entity.packet.*;

/**
 * @author Kevin
 * @Title: CommandeEum
 * @ProjectName studyjava
 * @Description: TODO 通信命令枚举
 * @date 2018/9/29 19:01
 */
public enum CommandEnum {
    LOGIN_REQUEST((byte)1, LoginRequestPacket.class),           //登录请求
    CONNECT_RESPONSE((byte)2, ConnectResponsePacket.class),     //连接响应
    LOGIN_RESPONSE((byte)3, LoginResponsePacket.class),         //登录响应
    COMMON((byte)0xff, CommonPacket.class);                     //普通命令（通用）

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

    CommandEnum(Byte code, Class<? extends Packet> clazz) {
        this.code = code;
        this.clazz = clazz;
    }

}
