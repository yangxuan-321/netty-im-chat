package com.netty.imchat.common.entity.packet;

import com.netty.imchat.common.enums.ProtoclEnum;
import com.netty.imchat.common.util.EnumUtils;

/**
 * @author Kevin
 * @Title: Packaet
 * @ProjectName studyjava
 * @Description: TODO 基础 通信包 抽象类
 * @date 2018/9/29 18:47
 */
public abstract class Packet {
    /**
     * 协议版本
     */
    private Byte version = EnumUtils.ByteEnumUtils.getEnumCode(ProtoclEnum.FIRST_VERSION);

    public Byte getVersion() {
        return version;
    }


    /**
     * 指令
     * @return
     */
    public abstract Byte getCommand();

    /**
     * 执行状态码
     */
    private int code;

    /**
     * 执行信息
     */
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
