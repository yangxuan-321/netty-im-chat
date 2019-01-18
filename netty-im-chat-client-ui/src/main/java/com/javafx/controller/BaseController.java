package com.javafx.controller;

import com.ClientUIMain;

/**
 * @author Kevin
 * @Title: BaseController
 * @ProjectName netty-im-chat
 * @Description: TODO
 * @date 2019/1/18 16:02
 */
public abstract class BaseController {
    //应用启动入口
    private ClientUIMain application;

    public void setApp(ClientUIMain application){
        this.application = application;
    }

    public ClientUIMain getApp(){
        return this.application;
    }

    public BaseController() {
        BaseUIInfo.CONTROLLER_LIST.put(getControllerCode(), this);
    }

    public abstract String getControllerCode();
}
