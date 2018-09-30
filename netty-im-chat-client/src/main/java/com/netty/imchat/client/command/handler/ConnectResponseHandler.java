package com.netty.imchat.client.command.handler;

import com.netty.imchat.common.entity.packet.ConnectResponsePacket;
import com.netty.imchat.common.entity.packet.Packet;
import com.netty.imchat.common.enums.CommandeEnum;
import com.netty.imchat.util.constant.Constant;
import com.netty.imchat.util.digest.Md5Utils;
import com.netty.imchat.util.exception.AppException;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author Kevin
 * @Title: ConnectResponseHandler
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/30 16:17
 */

@Component("com.netty.imchat.client.command.handler.AbstractClientCmdHandler")
public class ConnectResponseHandler extends AbstractClientCmdHandler {

    @Override
    public Byte getCode() {
        return CommandeEnum.CONNECT_RESPONSE.getCode();
    }

    @Override
    protected void executeCust(ChannelHandlerContext ctx, Object msg, Packet packet) {
        if(!(packet instanceof ConnectResponsePacket)){
            throw new AppException("pk不能为空"); //不能处理
        }

        ConnectResponsePacket connectResponsePacket = (ConnectResponsePacket) packet;
        if(StringUtils.isEmpty(connectResponsePacket.getPk())){
            throw new AppException("pk不能为空"); //pk不能为空
        }

        String pk = connectResponsePacket.getPk();
        int length = pk.length();
        String publicKey = pk.substring(0, length - Constant.MD5_LENGTH);
        String publicKeyMD5 = pk.substring(length - Constant.MD5_LENGTH);
        if(!Md5Utils.hash(publicKey).equals(publicKeyMD5)){
            throw new AppException("PK的摘要不对"); //PK的摘要不对
        }

        System.out.println("拿到pk");
    }
}
