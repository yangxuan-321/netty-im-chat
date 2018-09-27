package com.study.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author Kevin
 * @Title: NettyServer
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/27 18:23
 */
public class NettyServer {
    public static void main(String[] args){
        //启动器
        ServerBootstrap boot = new ServerBootstrap();
        //负责处理连接
        NioEventLoopGroup connect = new NioEventLoopGroup();
        //负责处理数据
        NioEventLoopGroup data = new NioEventLoopGroup();

        boot.
                group(connect, data).
                channel(NioServerSocketChannel.class).
                //处理子 group -- 即为数据处理
                childHandler(
                        //ChannelHandler   --  初始化 channel (主要目的是解码和输出)
                        new ChannelInitializer<NioSocketChannel>() {

                            @Override
                            protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                                //解码
                                nioSocketChannel.pipeline().addLast(new StringDecoder());
                                //输出
                                nioSocketChannel.pipeline().addLast(
                                        new SimpleChannelInboundHandler<String>() {

                                            @Override
                                            protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
                                                System.out.println(s);
                                            }
                                        }
                                );
                            }
                        }
                ).bind(8000);
    }
}
