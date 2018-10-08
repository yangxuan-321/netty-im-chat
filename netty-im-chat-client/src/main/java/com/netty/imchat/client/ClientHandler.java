package com.netty.imchat.client;

import com.netty.imchat.client.command.receive.handler.AbstractClientCmdHandler;
import com.netty.imchat.client.command.receive.manager.ClientCmdHandlerManager;
import com.netty.imchat.common.entity.packet.Packet;
import com.netty.imchat.common.util.PacketCodeUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kevin
 * @Title: ClientHandler
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/30 15:46
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = LoggerFactory.getLogger(ClientHandler.class);

    /**
     * 连接成功
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("-----连接成功------");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Packet packet = PacketCodeUtil.decode((ByteBuf) msg);

        AbstractClientCmdHandler cmdHandler = ClientCmdHandlerManager.getClientCmdHandler(packet.getCommand());
        cmdHandler.execute(ctx, msg, packet);
    }
}
