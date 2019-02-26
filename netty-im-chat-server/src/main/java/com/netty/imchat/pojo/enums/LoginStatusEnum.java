package com.netty.imchat.pojo.constant.enums;

import com.netty.imchat.common.entity.packet.*;

/**
 * @author : Kevin
 * @Title : LoginStatusEnum
 * @ProjectName netty-im-chat
 * @Description : TODO  登录状态枚举
 * @Time : Created in 2019/2/25 0:36
 * @Modifyed By :
 */
public enum LoginStatusEnum {
    NONE,       //未知
    SUCCESS,    //登陆成功
    FAIL,       //登录失败
    NOT,        //未登录
    TIMEOUT;    //超时断开
}
