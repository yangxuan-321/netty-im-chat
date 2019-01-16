package com.netty.imchat.client;

import com.netty.imchat.client.command.receive.handler.ConnectResponseHandler;
import com.netty.imchat.common.entity.vo.UserInfoVO;
import com.netty.imchat.util.exception.AppException;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;

/**
 * @author Kevin
 * @Title: BaseClient
 * @ProjectName netty-im-chat
 * @Description: TODO
 * @date 2018/10/8 18:42
 */
public class BaseClientInfo {

    private static BaseClientInfo this_;

    private BaseClientInfo(){
        super();
    }

    public static final BaseClientInfo instance(){
        synchronized (BaseClientInfo.class){
            if(null == this_){
                this_ = new BaseClientInfo();
            }
        }

        return this_;
    }

    //公钥
    private String publicKey;
    //ChannelFuture的作用是用来保存Channel异步操作的结果
    private ChannelFuture channelFuture;
    //登陆之后的token
    private String token;
    //登陆之后的用户信息
    private UserInfoVO userInfoVO;

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public ChannelFuture getChannelFuture() {
        return channelFuture;
    }

    /**
     * 连接成功之后将连接的信息保存到 这里
     * @param channelFuture
     */
    public void setChannelFuture(ChannelFuture channelFuture) {
        this.channelFuture = channelFuture;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInfoVO getUserInfoVO() {
        return userInfoVO;
    }

    public void setUserInfoVO(UserInfoVO userInfoVO) {
        this.userInfoVO = userInfoVO;
    }
}
