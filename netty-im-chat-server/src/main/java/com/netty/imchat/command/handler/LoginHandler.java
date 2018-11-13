package com.netty.imchat.command.handler;

import com.netty.imchat.common.entity.packet.ConnectResponsePacket;
import com.netty.imchat.common.entity.packet.LoginRequestPacket;
import com.netty.imchat.common.entity.packet.Packet;
import com.netty.imchat.common.enums.CommandEnum;
import com.netty.imchat.constant.Constants;
import com.netty.imchat.util.constant.Constant;
import com.netty.imchat.util.digest.RSAUtils;
import com.netty.imchat.util.exception.AppException;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Map;

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

        if(!(packet instanceof LoginRequestPacket)){
            throw new AppException("包结构不能被处理"); //不能处理
        }

        LoginRequestPacket loginPacket = (LoginRequestPacket) packet;

        String loginInfo = MessageFormat.format("-----登录操作:user->{0}, ip->{1}----",
                loginPacket.getLoginCode(), loginPacket.getPassword());

        log.info(loginInfo);

        //解密出用户和密码
        Map<String, String> loginInfoMap = decryptLoginInfo(loginPacket);
        if(null == loginInfoMap){

        }
    }

    private Map<String, String> decryptLoginInfo(LoginRequestPacket packet){
        Map<String, String> loginInfo = null;
        try {
            byte[] loginCodeBytes = RSAUtils.decryptByPublicKey(packet.getLoginCode().getBytes(), Constants.PRIVATE_KEY);
            byte[] passwordBytes = RSAUtils.decryptByPublicKey(packet.getPassword().getBytes(), Constants.PRIVATE_KEY);
            loginInfo.put("loginCode", new String(loginCodeBytes, Constants.ENCODING_UTF8));
            loginInfo.put("password", new String(passwordBytes, Constants.ENCODING_UTF8));
        }catch (Exception e){
            log.info(e.getMessage());
        }

        return loginInfo;
    }

    public static void main(String[] args) {
        new LoginHandler().getCode();
    }
}
