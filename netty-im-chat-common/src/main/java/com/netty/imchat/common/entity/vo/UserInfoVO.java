package com.netty.imchat.common.entity.vo;

/**
 * @author Kevin
 * @Title: UserInfoVO
 * @ProjectName netty-im-chat
 * @Description: TODO 用户信息 VO
 * @date 2018/11/13 15:50
 */
public class UserInfoVO {
    /**
     * 用户ID
     */
    private String userId = "";
    /**
     * 登录名
     */
    private String loginCode;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户昵称
     */
    private String nickName;

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public UserInfoVO(String loginCode, String userName, String nickName) {
        this.loginCode = loginCode;
        this.userName = userName;
        this.nickName = nickName;
    }
}
