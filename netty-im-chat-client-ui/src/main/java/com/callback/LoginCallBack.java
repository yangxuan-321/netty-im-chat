package com.callback;

import com.netty.imchat.client.callback.CallBack;
import com.netty.imchat.common.entity.packet.LoginResponsePacket;

/**
 * @author Kevin
 * @Title: LoginCallBack
 * @ProjectName netty-im-chat
 * @Description: TODO 登录回调
 * @date 2019/1/16 15:06
 */
public class LoginCallBack implements CallBack {

    @Override
    public void oncall(Object... args) {
        //登录事情
        System.out.println("-------登陆完毕--------");

        if(args.length != 1 && args[0] instanceof LoginResponsePacket){
            System.out.println("---参数不合法---");
            return;
        }

        LoginResponsePacket packet = (LoginResponsePacket)args[0];
        if(packet.isSuccess()){//登陆成功 -- 关闭登录窗口 打开 消息窗口

        }else{//登录失败 -- 弹窗提醒

        }

    }
}
