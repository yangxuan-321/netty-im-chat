package com.netty.imchat.client.command.send;

import com.netty.imchat.common.entity.packet.Packet;

/**
 * @author Kevin
 * @Title: Send
 * @ProjectName netty-im-chat
 * @Description: TODO
 * @date 2018/10/8 18:33
 */
public interface Send {
    public void send(Packet packet);
}
