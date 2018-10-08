package com.netty.imchat.run;

import com.netty.imchat.common.enums.CommandEnum;

/**
 * @author Kevin
 * @Title: TestMain
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/29 19:22
 */
public class TestMain {
    public static void main(String[] args) {
        System.out.println(CommandEnum.LOGIN_REQUEST instanceof Enum);
    }
}
