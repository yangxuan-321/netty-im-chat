package com.netty.imchat.client.pojo.dto;

import java.io.Serializable;

/**
 * @author : Kevin
 * @Title : MessageInfo
 * @ProjectName netty-im-chat
 * @Description : TODO 主要用于 包装消息 基类
 * @Time : Created in 2019/2/24 19:06
 * @Modifyed By :
 */
public class MessageInfo implements Serializable {

    private String messageContent;

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}
