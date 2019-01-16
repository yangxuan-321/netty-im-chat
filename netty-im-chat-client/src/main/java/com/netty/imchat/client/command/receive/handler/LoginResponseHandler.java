package com.netty.imchat.client.command.receive.handler;

import com.netty.imchat.client.callback.CallBack;
import com.netty.imchat.common.entity.packet.LoginResponsePacket;
import com.netty.imchat.common.entity.packet.Packet;
import com.netty.imchat.common.enums.CommandEnum;
import com.netty.imchat.util.exception.AppException;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

/**
 * @author Kevin
 * @Title: LoginResponseHandler
 * @ProjectName netty-im-chat
 * @Description: TODO 客户端处理登录响应 的 类
 * @date 2018/11/15 9:09
 */
@Component("com.netty.imchat.client.command.receive.handler.LoginResponseHandler")
public class LoginResponseHandler extends AbstractClientCmdHandler {

    public static CallBack callBack;

    @Override
    public Byte getCode() {
        return CommandEnum.LOGIN_RESPONSE.getCode();
    }

    @Override
    protected void executeCust(ChannelHandlerContext ctx, Object msg, Packet packet) {
        //如果不是登录响应包
        if(!(packet instanceof LoginResponsePacket)){
            throw new AppException("不合法的指令，LoginResponseHandler无法处理。");
        }

        //如果登录不成功
        LoginResponsePacket loginRes = (LoginResponsePacket) packet;
        if(!loginRes.isSuccess()){//登录不成功
            System.out.println(MessageFormat.format("-----登录不成功:{0}-----", loginRes.getMessage()));
        }

        //登陆成功 -- 保存相关消息
        super.BASE_CLIENT_INFO.setToken(loginRes.getToken());
        super.BASE_CLIENT_INFO.setUserInfoVO(loginRes.getUserInfoVO());

        if(null != callBack){
            callBack.oncall(packet);
        }

    }
}
