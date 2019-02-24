package com.netty.imchat.client.command.send;

import com.netty.imchat.client.BaseClientInfo;
import com.netty.imchat.common.entity.packet.LoginRequestPacket;
import com.netty.imchat.common.entity.packet.SingChatMessRequestPacket;
import com.netty.imchat.util.digest.Base64Utils;
import com.netty.imchat.util.digest.Md5Utils;
import com.netty.imchat.util.digest.RSAUtils;
import com.netty.imchat.util.exception.AppException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Kevin
 * @Title: MessageRequestSend
 * @ProjectName netty-im-chat
 * @Description: TODO
 * @date 2019/1/21 18:24
 */
@Component
public class MessageRequestSend {

    private BaseClientInfo clientInfo = BaseClientInfo.instance();

    public MessageRequestSend() {
        CommandFacde.MESSAGE_REQUEST_SEND = this;
    }

    @Resource(name="com.netty.imchat.client.command.send.BaseSend")
    private Send send;

    public void sendMessage(String message) {
        try {
            SingChatMessRequestPacket packet = new SingChatMessRequestPacket();
            packet.setUserId(123);
            packet.setMessContent(message+"我爱北京天安门");
            send.send(packet);

            //因为在那边会进行对象的序列化，如果出现了 粘包和半包的问题。将会发生异常
            //netty有实现好的各种类型拆包器 我们选择一种 [基于长度域拆包器]
            //testHalfPack(packet);
        }catch (Exception e){
            throw new AppException(e.getMessage());
        }
    }

    /**
     * 测试 粘包和半包的问题
     */
    public void testHalfPack(SingChatMessRequestPacket packet){
        for(int i = 0; i< 10000; i ++){
            send.send(packet);
        }
    }

}
