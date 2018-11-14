package com.netty.imchat.common.entity.packet;

import com.netty.imchat.common.enums.CommandEnum;

/**
 * @author Kevin
 * @Title: MessagePacket
 * @ProjectName netty-im-chat
 * @Description: TODO 消息报文包 -- 单聊请求
 * @date 2018/11/14 13:51
 */
public class SingChatMessRequestPacket extends AbsChatMessRequestPacket {
    @Override
    public Byte getCommand() {
        return CommandEnum.SING_CHAT_MESS_REQEST.getCode();
    }

    /**
     * 接收人的ID
     */
    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
