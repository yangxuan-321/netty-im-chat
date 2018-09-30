package com.netty.imchat.common.entity.packet;

import com.netty.imchat.common.enums.CommandeEnum;

/**
 * @author Kevin
 * @Title: ConnectResponsePacket
 * @ProjectName studyjava
 * @Description: TODO  连接返回类 主要是 返回一个公钥
 * @date 2018/9/30 11:47
 */
public class ConnectResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return CommandeEnum.CONNECT_RESPONSE.getCode();
    }

    /**
     * 公钥
     */
    private String pk;

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }
}
