package com.netty.imchat.client.pojo.dto;

/**
 * @author : Kevin
 * @Title : SingChatMessageInfo
 * @ProjectName netty-im-chat
 * @Description : TODO 单聊 的 消息类
 * @Time : Created in 2019/2/24 19:11
 * @Modifyed By :
 */
public class SingChatMessageInfo extends MessageInfo {
    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
