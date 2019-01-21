package com.controller;

import com.ClientUIMain;
import com.cache.BaseUIInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * @author Kevin
 * @Title: BaseController
 * @ProjectName netty-im-chat
 * @Description: TODO
 * @date 2019/1/18 16:02
 */
public abstract class BaseController {

    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    //应用启动入口
    private ClientUIMain application;

    public BaseController() {
    }

    public void setController(){
        log.info(MessageFormat.format("-----{0}-----", getControllerCode()));
        BaseUIInfo.CONTROLLER_LIST.put(getControllerCode(), this);
    }

    public abstract String getControllerCode();
}
