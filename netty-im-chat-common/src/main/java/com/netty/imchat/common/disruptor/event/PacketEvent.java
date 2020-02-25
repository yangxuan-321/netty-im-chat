package com.netty.imchat.common.disruptor.event;

import com.netty.imchat.common.entity.packet.Packet;
import io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : MODA-Master
 * @Title : TransDataEvent
 * @ProjectName disruptor-netty
 * @Description : TODO
 * @Time : Created in 2020/2/24 20:16
 * @Modifyed By :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacketEvent {
    private Packet packet;

    private ChannelHandlerContext ctx;

    private Object msg;
}
