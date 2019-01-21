package com.study.netty.handler;

import com.study.util.DateTimeUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * @author Kevin
 * @Title: FirstClientHandler
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/29 16:32
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger log = LoggerFactory.getLogger(ChannelInboundHandlerAdapter.class);

    /**
     * 重写 channelActive方法。在连接被建立之后就会被调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //1.获取数据
        ByteBuf buf = getData(ctx);
        log.info("-------客户端写出消息-------");
        //2.向服务器写数据
        ctx.channel().writeAndFlush(buf);
    }

    /**
     * 获取数据
     * @param ctx
     * @return
     */
    private ByteBuf getData(ChannelHandlerContext ctx){
        ByteBuf buf = ctx.alloc().buffer();
        buf.writeBytes((DateTimeUtil.getNowTime()+" hello world").getBytes(Charset.forName("UTF-8")));
        return buf;
    }

    /**
     * 当有数据来到 需要读取时 就会触发方法
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        read(msg);
    }

    private void read(Object msg){
        ByteBuf byteBuf = (ByteBuf)msg;
        String data = byteBuf.toString(Charset.forName("UTF-8"));
        log.info("客户端收到消息: " + data);
    }
}
