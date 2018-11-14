package com.netty.imchat.common.entity.packet;

/**
 * @author Kevin
 * @Title: AbsChatMessReqPacket
 * @ProjectName netty-im-chat
 * @Description: TODO
 * @date 2018/11/14 18:33
 */
public abstract class AbsChatMessRequestPacket extends Packet {
    /**
     * 消息内容
     */
    private String messContent;

    public String getMessContent() {
        return messContent;
    }

    public void setMessContent(String messContent) {
        this.messContent = messContent;
    }
}
