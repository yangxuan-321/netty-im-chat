package com.study.netty;

import com.study.netty.handler.FirstServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kevin
 * @Title: NettyServer
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/27 18:23
 */
public class NettyServer {

    private static final Logger log = LoggerFactory.getLogger(NettyServer.class);

    public static void main(String[] args){
        //启动器 -- 创建引导类
        ServerBootstrap boot = new ServerBootstrap();
        //负责处理连接  --  相当于老板（在外面接活）
        NioEventLoopGroup connectGroup = new NioEventLoopGroup();
        //负责处理数据  --  相当于员工（做老板接的活）
        NioEventLoopGroup dataGroup = new NioEventLoopGroup();
        //老板（connectGroup）在外面接活，然后丢给员工去做
        //员工（dataGroup）员工干活

        //给引导类 配置两大线程组，这个类的线程模型也就定型了
        boot.
                group(connectGroup, dataGroup).
                //制定服务端的IO模型 -- 当然，这里也有其他的选择，如果你想指定 IO 模型为 BIO，那么这里配置上OioServerSocketChannel.class类型即可，当然通常我们也不会这么做，因为Netty的优势就在于NIO。
                channel(NioServerSocketChannel.class).
                //处理子 group -- 即为数据处理
                childHandler(
                        //ChannelHandler   --  初始化 channel (主要目的是解码和输出)
                        new ChannelInitializer<NioSocketChannel>() {

                            @Override
                            protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                                //获取服务端侧关于这条连接的逻辑处理链 pipeline
                                ChannelPipeline pipeline = nioSocketChannel.pipeline();
                                pipeline.addLast(new FirstServerHandler());

//                                //解码
//                                nioSocketChannel.pipeline().addLast(new StringDecoder());
//                                //输出
//                                nioSocketChannel.pipeline().addLast(
//                                        new SimpleChannelInboundHandler<String>() {
//
//                                            @Override
//                                            protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
//                                                System.out.println(s);
//                                            }
//                                        }
//                                );
                            }
                        }
                );

        //绑定8000端口
//        boot.bind(8000);

        //自动绑定递增端口 , 假设8000端口被占用 我们需要寻找其他端口绑定

        bind(boot, 8000);
    }

    private static void bind(ServerBootstrap boot, final int port){
        boot.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()){
                    log.info("成功绑定端口:"+ port);
                }else{
                    log.info("失败绑定端口:" + port);
                    //端口 是 0~65535 [可以相应设计白名单/黑名单]
                    if(port >= 65535){
                        log.info("所有端口都绑定不成功");
                        return;
                    }
                    bind(boot, port+1);
                }
            }
        });
    }
}
