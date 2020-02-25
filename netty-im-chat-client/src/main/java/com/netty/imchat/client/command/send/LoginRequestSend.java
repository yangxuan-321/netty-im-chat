package com.netty.imchat.client.command.send;

import com.netty.imchat.client.BaseClientInfo;
import com.netty.imchat.common.entity.packet.LoginRequestPacket;
import com.netty.imchat.util.digest.Base64Utils;
import com.netty.imchat.util.digest.Md5Utils;
import com.netty.imchat.util.digest.RSAUtils;
import com.netty.imchat.util.exception.AppException;
import com.netty.imchat.util.general.StringUtils;
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
            if (StringUtils.isEmpty(clientInfo.getPublicKey())){
                // 私钥还没准备好
                return;
            }
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
