package com.netty.imchat.util.login;

import com.netty.imchat.pojo.constant.Attributes;
import com.netty.imchat.pojo.constant.enums.LoginStatusEnum;
import io.netty.channel.Channel;

/**
 * @author : Kevin
 * @Title : LoginUtil
 * @ProjectName netty-im-chat
 * @Description : TODO
 * @Time : Created in 2019/2/25 0:30
 * @Modifyed By :
 */
public class LoginUtil {

    /**
     * 标记该channel为登录状态
     * @param channel
     */
    public static final void markAsLogin(Channel channel){
        if(null == channel){
            return;
        }

        channel.attr(Attributes.LOGIN).set(LoginStatusEnum.SUCCESS);
    }

    /**
     * 判断是否登陆成功
     * @param channel
     */
    public static final boolean hasLogin(Channel channel){
        if(null == channel){
            return false;
        }

        LoginStatusEnum loginStatusEnum = channel.attr(Attributes.LOGIN).get();

        return LoginStatusEnum.SUCCESS == loginStatusEnum ? true : false;
    }
}
