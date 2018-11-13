package com.netty.imchat.common.entity.packet;

import com.netty.imchat.common.enums.CommandEnum;

/**
 * @author Kevin
 * @Title: LoginRequestPacket
 * @ProjectName studyjava
 * @Description: TODO 登录请求包
 * @date 2018/9/29 19:06
 */
public class LoginRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return CommandEnum.LOGIN_REQUEST.getCode();
    }

    /**
     * 用户姓名
     */
    private String loginCode;

    /**
     * 用户密码
     */
    private String password;


    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
