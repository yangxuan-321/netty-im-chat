package com.netty.imchat.common.entity.packet;

import com.netty.imchat.common.entity.vo.UserInfoVO;
import com.netty.imchat.common.enums.CommandEnum;
import lombok.Data;

/**
 * @author Kevin
 * @Title: LoginResponsePacket
 * @ProjectName netty-im-chat
 * @Description: TODO 注册响应包
 * @date 2018/11/13 15:46
 */
@Data
public class RegisterResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return CommandEnum.REGISTER_REPONSE.getCode();
    }

    private UserInfoVO userInfoVO;

    private String token;
}
