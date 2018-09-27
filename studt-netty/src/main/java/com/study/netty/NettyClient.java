package com.study.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * @author Kevin
 * @Title: NettyClient
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/27 19:02
 */
public class NettyClient {
    public static void main(String[] args) throws Exception{
        Bootstrap boot = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        boot.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>(){

                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new StringEncoder());
                    }
                });

        Channel channel = boot.connect(new InetSocketAddress("127.0.0.1", 8000)).channel();

        int i = 0;
        while (true){
            channel.writeAndFlush("yangxuan"+i++);
            Thread.sleep(2000);
        }
    }
}
