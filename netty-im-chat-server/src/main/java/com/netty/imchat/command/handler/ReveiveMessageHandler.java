package com.netty.imchat.command.handler;

import com.netty.imchat.common.entity.packet.LoginResponsePacket;
import com.netty.imchat.common.entity.packet.Packet;
import com.netty.imchat.common.entity.packet.SingChatMessRequestPacket;
import com.netty.imchat.common.entity.vo.UserInfoVO;
import com.netty.imchat.common.enums.CommandEnum;
import com.netty.imchat.util.constant.HttpStatus;
import com.netty.imchat.util.exception.AppException;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Kevin
 * @Title: LoginHandler
 * @ProjectName netty-im-chat
 * @Description: TODO 服务端处理登录 的 处理类
 * @date 2018/11/13 11:05
 */
@Component("com.netty.imchat.command.handler.ReveiveMessageHandler")
public class ReveiveMessageHandler extends AbstractServerCmdHandler {

    public static final Logger log = LoggerFactory.getLogger(ReveiveMessageHandler.class);

    @Override
    public Byte getCode() {
        return CommandEnum.SING_CHAT_MESS_REQEST.getCode();
    }

    @Override
    protected void executeCust(ChannelHandlerContext ctx, Object msg, Packet packet) {
        UserInfoVO userInfoVO = null;
        LoginResponsePacket responsePacket = new LoginResponsePacket();
        try {
            if(!(packet instanceof SingChatMessRequestPacket)){
                throw new AppException("包结构不能被处理"); //不能处理
            }

            SingChatMessRequestPacket messRequestPacket = (SingChatMessRequestPacket) packet;


            log.info(messRequestPacket.getMessContent());

        }catch (Exception e){
            responsePacket.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            if(e instanceof AppException){
                responsePacket.setMessage(e.getMessage());
            }else{
                responsePacket.setMessage("未知错误");
            }
        }

        //2.将响应信息回写 客户端
        //PacketWriteUtil.writeRes(responsePacket, ctx);
    }
}
