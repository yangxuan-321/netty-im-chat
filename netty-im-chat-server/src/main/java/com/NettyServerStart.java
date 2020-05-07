package com;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.ProducerType;
import com.netty.imchat.common.disruptor.MessageConmuser;
import com.netty.imchat.common.disruptor.RingBufferWorkerPoolFactory;
import com.netty.imchat.disruptor.consumer.ServerMessageConsumer;
import com.netty.imchat.protocol.server.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.MessageFormat;

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
        SpringApplication.run(NettyServerStart.class, args);

        // 1.创建 disruptor 的 组件
        MessageConmuser[] conmusers = new ServerMessageConsumer[8 * 2];
        for (int i = 0; i < conmusers.length; i++) {
            conmusers[i] = new ServerMessageConsumer(MessageFormat.format("disruptor::server-conmuser::{0}", i));
        }
        RingBufferWorkerPoolFactory.getInstance().initAndStart(ProducerType.MULTI,
                1024 * 1024, new BlockingWaitStrategy(), conmusers);

        //启动
        NettyServer.run();
    }
}
