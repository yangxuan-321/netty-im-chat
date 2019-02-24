package com.netty.imchat.util.network;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;

import java.net.InetSocketAddress;

/**
 * @author : Kevin
 * @Title : IPUtils
 * @ProjectName netty-im-chat
 * @Description : TODO 关于获取IP的一些方法
 * @Time : Created in 2019/2/24 14:57
 * @Modifyed By :
 */
public class IPUtils {
    /**
     * 获取客户端的ip地址
     * @param ctx
     * @param msg
     * @return
     */
    public static String obtainNettyClientIP(ChannelHandlerContext ctx){
                InetSocketAddress insocket = (InetSocketAddress) ctx.channel()
                        .remoteAddress();
                String clientIP = insocket.getAddress().getHostAddress();

            return clientIP;
    }
}
