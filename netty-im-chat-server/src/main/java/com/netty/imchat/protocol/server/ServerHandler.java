package com.netty.imchat.protocol.server;

import com.netty.imchat.command.handler.AbstractServerCmdHandler;
import com.netty.imchat.command.manager.ServerCmdHandlerManager;
import com.netty.imchat.common.entity.packet.ConnectResponsePacket;
import com.netty.imchat.common.entity.packet.Packet;
import com.netty.imchat.common.util.PacketCodeUtil;
import com.netty.imchat.constant.Constants;
import com.netty.imchat.util.digest.Md5Utils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetSocketAddress;

/**
 * @author Kevin
 * @Title: ServerMessageHandler
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/30 15:05
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    //在连接后被调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress socketAddress = (InetSocketAddress)ctx.channel().remoteAddress();
        System.out.println("--------客户端连接进来了:ip"+ socketAddress.getAddress() +"--------");

        //返回给客户端一个公钥  --  其实整个过程还是有可能被 窃取 (例如公钥被调换，所以 https的证书很重要)
        //将返回给客户端的消息定义为
        connectResDataWrite(ctx);
    }

    //写出
    private void connectResDataWrite(ChannelHandlerContext ctx){
        ByteBuf byteBuf = packConnectResData();
        ctx.channel().writeAndFlush(byteBuf);
    }

    //组装序列化对象
    private ByteBuf packConnectResData(){
        // 将公钥 + MD5的校验发过去
        String res = Constants.PUBLIC_KEY + Md5Utils.hash(Constants.PUBLIC_KEY);
        Packet packet = new ConnectResponsePacket();
        ((ConnectResponsePacket) packet).setPk(res);

        return PacketCodeUtil.encode(packet);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Packet packet = PacketCodeUtil.decode((ByteBuf) msg);

        AbstractServerCmdHandler cmdHandler = ServerCmdHandlerManager.getClientCmdHandler(packet.getCommand());
        cmdHandler.execute(ctx, msg, packet);
    }
}
