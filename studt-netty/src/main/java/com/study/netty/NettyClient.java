package com.study.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * @author Kevin
 * @Title: NettyClient
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/27 19:02
 */
public class NettyClient {
    /**
     * 重试次数
     */
    private static final int MAX_RETRY = 5;

    /**
     * 对于客户端启动来说，和服务端的启动类似，依然需要线程模型、IO模型、以及IO业务处理逻辑三大参数。
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        //创建启动类
        Bootstrap boot = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        //1.指定线程模型
        boot.group(group)
                //2.指定IO模型
                .channel(NioSocketChannel.class)
                //3.设定IO处理逻辑
                .handler(new ChannelInitializer<SocketChannel>(){

                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new StringEncoder());
                    }
                });

        //4.建立连接
        //Channel channel = boot.connect(new InetSocketAddress("127.0.0.1", 8000)).channel();
        //此处连接有可能失败。因此需要有一定的重连机制
        try {
            ChannelFuture connect = connect(boot, "127.0.0.1", 8000, 5);
            int i = 0;
            while (true){
                connect.channel().writeAndFlush("yangxuan"+i++);
                Thread.sleep(2000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 通常情况下，连接建立失败不会立即重新建立连接，而是会通过一个指数退避的方式。
     * 比如每个 1, 2, 4, 8, ...以2的次方来建立连接，然后重试一定次数后就放弃连接。
     * 本方法重试5次
     * @return
     */
    private static ChannelFuture connect(Bootstrap boot, String host, int port, int retryCount){
        return boot.connect(host, port).addListener(future -> {
            if(future.isSuccess()){
                System.out.println("客户端连接建立成功");
            }else if(retryCount == 0){
                System.out.println("重试连接次数已经用完，放弃连接");
                throw new RuntimeException("重试连接次数已经用完，放弃连接");
            }else{
                //重连的次数
                int count = MAX_RETRY - retryCount + 1;
                System.out.println("连接失败,第"+ count +"次重连");
                //重连的间隔
                int delay = 1 << count;
                boot.config().group().schedule(()->{connect(boot, host, port, retryCount - 1);}, delay, TimeUnit.SECONDS);
            }
        });
    }
}
