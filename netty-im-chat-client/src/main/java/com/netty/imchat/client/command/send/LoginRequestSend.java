package com.netty.imchat.client.command.send;

import com.netty.imchat.client.BaseClientInfo;
import com.netty.imchat.common.entity.packet.ConnectResponsePacket;
import com.netty.imchat.common.entity.packet.LoginRequestPacket;
import com.netty.imchat.common.entity.packet.Packet;
import com.netty.imchat.common.enums.CommandEnum;
import com.netty.imchat.common.util.PacketCodeUtil;
import com.netty.imchat.util.constant.Constant;
import com.netty.imchat.util.digest.Base64Utils;
import com.netty.imchat.util.digest.Md5Utils;
import com.netty.imchat.util.digest.RSAUtils;
import com.netty.imchat.util.exception.AppException;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Kevin
 * @Title: LoginRequestHandler
 * @ProjectName netty-im-chat
 * @Description: TODO
 * @date 2018/10/8 17:19
 */
@Component("com.netty.imchat.client.command.send.LoginRequestSend")
public class LoginRequestSend {

    private BaseClientInfo clientInfo = BaseClientInfo.instance();

    public LoginRequestSend() {
        CommandFacde.LOGIN_REQUEST_SEND = this;
    }

    @Resource(name="com.netty.imchat.client.command.send.BaseSend")
    private Send send;

    public void login(String loginCode, String password) {
        try {
            LoginRequestPacket packet = new LoginRequestPacket();
            byte[] passwd = RSAUtils.encryptByPublicKey(password.getBytes(), clientInfo.getPublicKey());
            String encode = Base64Utils.encode(passwd);
            String hash = Md5Utils.hash(encode);
            packet.setPassword(encode+hash);
            packet.setLoginCode(loginCode);
            send.send(packet);
        }catch (Exception e){
            throw new AppException(e.getMessage());
        }
    }
}
