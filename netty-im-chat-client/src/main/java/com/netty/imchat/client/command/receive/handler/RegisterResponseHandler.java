package com.netty.imchat.client.command.receive.handler;

import com.netty.imchat.client.callback.CallBack;
import com.netty.imchat.common.entity.packet.LoginResponsePacket;
import com.netty.imchat.common.entity.packet.Packet;
import com.netty.imchat.common.entity.packet.RegisterResponsePacket;
import com.netty.imchat.common.enums.CommandEnum;
import com.netty.imchat.util.exception.AppException;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

/**
 * @author Kevin
 * @Title: LoginResponseHandler
 * @ProjectName netty-im-chat
 * @Description: TODO 客户端处理注册响应 的 类
 * @date 2018/11/15 9:09
 */
@Component("com.netty.imchat.client.command.receive.handler.RegisterResponseHandler")
public class RegisterResponseHandler extends AbstractClientCmdHandler {

    private static final Logger log = LoggerFactory.getLogger(RegisterResponseHandler.class);

    public static CallBack callBack;

    @Override
    public Byte getCode() {
        return CommandEnum.LOGIN_RESPONSE.getCode();
    }

    @Override
    protected void executeCust(ChannelHandlerContext ctx, Object msg, Packet packet) {
        //如果不是注册响应包
        if(!(packet instanceof RegisterResponsePacket)){
            throw new AppException("不合法的指令，RegisterResponseHandler无法处理。");
        }

        //如果登录不成功
        RegisterResponsePacket loginRes = (RegisterResponsePacket) packet;
        if(!loginRes.isSuccess()){//登录不成功
            log.info(MessageFormat.format("-----注册不成功:{0}-----", loginRes.getMessage()));
        }

        //登陆成功 -- 保存相关消息
        super.BASE_CLIENT_INFO.setToken(loginRes.getToken());
        super.BASE_CLIENT_INFO.setUserInfoVO(loginRes.getUserInfoVO());

        if(null != callBack){
            callBack.oncall(packet);
        }

    }
}
