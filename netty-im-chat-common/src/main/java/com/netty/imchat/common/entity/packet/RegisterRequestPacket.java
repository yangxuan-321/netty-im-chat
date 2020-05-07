package com.netty.imchat.common.entity.packet;

import com.netty.imchat.common.enums.CommandEnum;
import lombok.Data;

/**
 * @author Kevin
 * @Title: LoginRequestPacket
 * @ProjectName studyjava
 * @Description: TODO 注册请求包
 * @date 2018/9/29 19:06
 */
@Data
public class RegisterRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return CommandEnum.REGISTER_REQUEST.getCode();
    }

    /**
     * 账号
     */
    private String loginCode;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户密码
     */
    private String password;
}
