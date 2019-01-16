package com.netty.imchat.common.entity.packet;

import com.netty.imchat.common.entity.vo.UserInfoVO;
import com.netty.imchat.common.enums.CommandEnum;

/**
 * @author Kevin
 * @Title: LoginResponsePacket
 * @ProjectName netty-im-chat
 * @Description: TODO 登录响应包
 * @date 2018/11/13 15:46
 */
public class LoginResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return CommandEnum.LOGIN_RESPONSE.getCode();
    }

    private UserInfoVO userInfoVO;

    private String token;

    public UserInfoVO getUserInfoVO() {
        return userInfoVO;
    }

    public void setUserInfoVO(UserInfoVO userInfoVO) {
        this.userInfoVO = userInfoVO;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
