package com.netty.imchat.pojo.constant;

import io.netty.util.AttributeKey;
import com.netty.imchat.pojo.constant.enums.LoginStatusEnum;
/**
 * @author : Kevin
 * @Title : Attributes
 * @ProjectName netty-im-chat
 * @Description : TODO 属性常量类
 * @Time : Created in 2019/2/25 0:33
 * @Modifyed By :
 */
public class Attributes {
    public static final AttributeKey<LoginStatusEnum> LOGIN = AttributeKey.valueOf("login_status");
}
