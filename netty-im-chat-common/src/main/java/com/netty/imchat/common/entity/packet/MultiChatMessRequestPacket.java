package com.netty.imchat.common.entity.packet;

import com.netty.imchat.common.enums.CommandEnum;

import java.util.List;

/**
 * @author Kevin
 * @Title: MultiChatMessRequestPacket
 * @ProjectName netty-im-chat
 * @Description: TODO
 * @date 2018/11/14 18:38
 */
public class MultiChatMessRequestPacket extends AbsChatMessRequestPacket {
    @Override
    public Byte getCommand() {
        return CommandEnum.MULTI_CHAT_MESS_REQEST.getCode();
    }

    /**
     * 群组ID
     */
    private String groupId;

    /**
     * 是否 @ 所有人
     */
    private byte isAtAll;

    /**
     * @ 的人员列表
     */
    private List<Long> atUserIds;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public byte getIsAtAll() {
        return isAtAll;
    }

    public void setIsAtAll(byte isAtAll) {
        this.isAtAll = isAtAll;
    }

    public List<Long> getAtUserIds() {
        return atUserIds;
    }

    public void setAtUserIds(List<Long> atUserIds) {
        this.atUserIds = atUserIds;
    }
}
