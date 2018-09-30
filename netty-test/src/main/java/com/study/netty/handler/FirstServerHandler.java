package com.study.netty.handler;

import com.study.util.DateTimeUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * @author Kevin
 * @Title: FirstServerHandler
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/29 16:45
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 这个方法在接收到客户端发来的数据之后被回调
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //1.服务端接收到来自客户端的消息
        read(msg);

        //2.服务端收到消息之后 向客户端回写一个消息
        write(ctx);
    }

    /**
     * 读取消息
     * @param msg
     */
    private void read(Object msg){
        ByteBuf byteBuf = (ByteBuf) msg;
        String data = byteBuf.toString(Charset.forName("UTF-8"));
        System.out.println("服务端收到消息: " + data);
    }

    /**
     * 写出消息
     * @param ctx
     */
    private void write(ChannelHandlerContext ctx){
        ByteBuf byteBuf = ctx.alloc().buffer();
        System.out.println("-------服务端写出消息-------");
        byteBuf.writeBytes((DateTimeUtil.getNowTime() + " 服务端已经读取到消息").getBytes(Charset.forName("UTF-8")));
        ctx.writeAndFlush(byteBuf);
    }
}
