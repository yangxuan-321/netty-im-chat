package com.netty.imchat.client;

import com.netty.imchat.common.splitpack.SplitPackageDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author Kevin
 * @Title: NettyClient
 * @ProjectName studyjava
 * @Description: TODO       nettyClient的主要启动流程
 * @date 2018/9/30 15:38
 */
public class NettyClient {

    private static final Logger log = LoggerFactory.getLogger(NettyClient.class);

    private static BaseClientInfo clientInfo = BaseClientInfo.instance();

    public static final Integer MAX_RETRY = 5;

    public static void run() {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.
                group(group).
                channel(NioSocketChannel.class).
                handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        //可以简单把 pipeline 理解成责任链
                        //1.客户端接受消息的拆包处理(解决粘包和半包)
                        nioSocketChannel.pipeline().addLast(new SplitPackageDecoder());
                        //2.客户端接受消息的逻辑处理
                        nioSocketChannel.pipeline().addLast(new ClientHandler());
                    }
                });

        //4.建立连接
        //此处连接有可能失败。因此需要有一定的重连机制
        try {
            ChannelFuture connect = connect(bootstrap, "127.0.0.1", 8000, 5);
            clientInfo.setChannelFuture(connect);
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
                log.info("客户端连接建立成功");
            }else if(retryCount == 0){
                log.info("重试连接次数已经用完，放弃连接");
                throw new RuntimeException("重试连接次数已经用完，放弃连接");
            }else{
                //重连的次数
                int count = MAX_RETRY - retryCount + 1;
                log.info("连接失败,第"+ count +"次重连");
                //重连的间隔
                int delay = 1 << count;
                boot.config().group().schedule(()->{connect(boot, host, port, retryCount - 1);}, delay, TimeUnit.SECONDS);
            }
        });
    }
}
