package com.netty.imchat.client;

import com.netty.imchat.client.command.receive.handler.ConnectResponseHandler;
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
        if(null == this_){
            this_ = new BaseClientInfo();
        }

        return this_;
    }

    //公钥
    private String publicKey;
    //启动类信息
    private ChannelFuture channelFuture;

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public ChannelFuture getChannelFuture() {
        return channelFuture;
    }

    public void setChannelFuture(ChannelFuture channelFuture) {
        this.channelFuture = channelFuture;
    }
}
