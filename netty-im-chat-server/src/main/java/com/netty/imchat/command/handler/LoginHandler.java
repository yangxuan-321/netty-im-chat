package com.netty.imchat.command.handler;

import com.netty.imchat.common.entity.packet.ConnectResponsePacket;
import com.netty.imchat.common.entity.packet.LoginRequestPacket;
import com.netty.imchat.common.entity.packet.Packet;
import com.netty.imchat.common.enums.CommandEnum;
import com.netty.imchat.util.exception.AppException;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

/**
 * @author Kevin
 * @Title: LoginHandler
 * @ProjectName netty-im-chat
 * @Description: TODO
 * @date 2018/11/13 11:05
 */
@Component("com.netty.imchat.command.handler.LoginHandler")
public class LoginHandler extends AbstractServerCmdHandler {

    public static final Logger log = LoggerFactory.getLogger(LoginHandler.class);

    @Override
    public Byte getCode() {
        return CommandEnum.LOGIN_REQUEST.getCode();
    }

    @Override
    protected void executeCust(ChannelHandlerContext ctx, Object msg, Packet packet) {

        if(!(packet instanceof ConnectResponsePacket)){
            throw new AppException("包结构不能被处理"); //不能处理
        }

        LoginRequestPacket loginPacket = (LoginRequestPacket) packet;

        MessageFormat.format("-----登录操作:user->{0}, ip->{1}----",
                loginPacket.getLoginCode(), loginPacket.getPassword());

    }
}
