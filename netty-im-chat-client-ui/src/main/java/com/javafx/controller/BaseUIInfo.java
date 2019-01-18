package com.javafx.controller;

import com.ClientUIMain;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kevin
 * @Title: BaseClientInfo
 * @ProjectName netty-im-chat
 * @Description: TODO
 * @date 2019/1/18 16:11
 */
public class BaseUIInfo {
    public static ClientUIMain application;

    public static final Map<String, BaseController> CONTROLLER_LIST = new HashMap<String, BaseController>();

}
