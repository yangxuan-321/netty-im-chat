package com.netty.imchat.common.entity.packet;

import com.netty.imchat.common.enums.CommandEnum;

/**
 * @author Kevin
 * @Title: CommonPacket
 * @ProjectName netty-im-chat
 * @Description: TODO
 * @date 2018/11/13 18:20
 */
public class CommonPacket extends Packet {
    @Override
    public Byte getCommand() {
        return CommandEnum.COMMON.getCode();
    }
}
