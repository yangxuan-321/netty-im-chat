package com.netty.imchat.protocol.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Kevin
 * @Title: NettyServerStart
 * @ProjectName studyjava
 * @Description: TODO netty服务器启动
 * @date 2018/9/30 9:31
 */
@SpringBootApplication
public class NettyServerStart {
    public static void main(String[] args) {
        //启动
        NettyServer.run();
    }
}
