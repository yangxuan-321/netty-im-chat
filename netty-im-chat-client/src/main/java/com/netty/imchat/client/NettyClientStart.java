package com.netty.imchat.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Kevin
 * @Title: NettyClientStart
 * @ProjectName studyjava
 * @Description: TODO  NettyClient的启动类
 * @date 2018/9/30 15:39
 */
@SpringBootApplication
public class NettyClientStart {
    public static void main(String[] args) {
        SpringApplication.run(NettyClientStart.class, args);
        NettyClient.run();
    }
}
