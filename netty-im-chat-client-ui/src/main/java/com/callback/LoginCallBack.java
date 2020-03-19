package com.callback;

import com.controller.BaseController;
import com.route.node.NodeRouter;
import com.cache.BaseUIInfo;
import com.controller.LoginController;
import com.netty.imchat.client.callback.CallBack;
import com.netty.imchat.common.entity.packet.LoginResponsePacket;
import javafx.application.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kevin
 * @Title: LoginCallBack
 * @ProjectName netty-im-chat
 * @Description: TODO 登录回调
 * @date 2019/1/16 15:06
 */
public class LoginCallBack implements CallBack {

    private static final Logger log = LoggerFactory.getLogger(LoginCallBack.class);

    @Override
    public void oncall(Object... args) {
        //登录事情
        log.info("-------登陆完毕--------");

        if(args.length != 1 && args[0] instanceof LoginResponsePacket){
            log.info("---参数不合法---");
            return;
        }

        LoginResponsePacket packet = (LoginResponsePacket)args[0];
        if(packet.isSuccess()){//登陆成功 -- 关闭登录窗口 打开 消息窗口
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    //更新JavaFX的主线程的代码放在此处
                    NodeRouter.gotoMessage();
                }
            });
        }else{//登录失败 -- 弹窗提醒
            //暂时先不做啥
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    //更新JavaFX的主线程的代码放在此处
                    BaseController controller = BaseUIInfo.CONTROLLER_LIST.get(LoginController.CONTROLLER_CODE);
                    ((LoginController)controller).setLoginTips(packet);
                }
            });
        }
    }
}
