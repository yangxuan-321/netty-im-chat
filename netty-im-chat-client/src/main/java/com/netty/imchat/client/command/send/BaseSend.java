package com.netty.imchat.client.command.send;

import com.netty.imchat.client.BaseClientInfo;
import com.netty.imchat.common.entity.packet.Packet;
import com.netty.imchat.common.util.PacketCodeUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import org.springframework.stereotype.Component;

/**
 * @author Kevin
 * @Title: BaseSend
 * @ProjectName netty-im-chat
 * @Description: TODO
 * @date 2018/10/8 19:28
 */
@Component("com.netty.imchat.client.command.send.BaseSend")
public class BaseSend implements Send {

    public BaseClientInfo clientInfo = BaseClientInfo.instance();

    @Override
    public void send(Packet packet) {
        ChannelFuture channelFuture = clientInfo.getChannelFuture();
        Channel channel = channelFuture.channel();
        ByteBuf byteBuf = PacketCodeUtil.encode(packet);
        channel.writeAndFlush(byteBuf);
    }
}
