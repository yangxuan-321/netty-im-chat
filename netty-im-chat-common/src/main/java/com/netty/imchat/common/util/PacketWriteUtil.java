package com.netty.imchat.common.util;

import com.netty.imchat.common.entity.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author Kevin
 * @Title: PacketWriteUtil
 * @ProjectName netty-im-chat
 * @Description: TODO 对数据包进行 写出的 工具类
 * @date 2018/11/13 17:21
 */
public class PacketWriteUtil {
    public static void writeRes(Packet packet, ChannelHandlerContext ctx){
        ByteBuf byteBuf = PacketCodeUtil.encode(packet);
        ctx.channel().writeAndFlush(byteBuf);
    }
}
