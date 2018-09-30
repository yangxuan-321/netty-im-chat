package com.netty.imchat.client;

import com.netty.imchat.client.command.handler.AbstractClientCmdHandler;
import com.netty.imchat.client.command.manager.ClientCmdHandlerManager;
import com.netty.imchat.common.entity.packet.ConnectResponsePacket;
import com.netty.imchat.common.entity.packet.Packet;
import com.netty.imchat.common.util.PacketCodeUtil;
import com.netty.imchat.util.constant.Constant;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Kevin
 * @Title: ClientHandler
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/30 15:46
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 连接成功
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("-----连接成功------");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Packet packet = PacketCodeUtil.decode((ByteBuf) msg);

        AbstractClientCmdHandler cmdHandler = ClientCmdHandlerManager.getClientCmdHandler(packet.getCommand());
        cmdHandler.execute(ctx, msg, packet);
    }
}
