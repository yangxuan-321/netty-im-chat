package com.netty.imchat.command.handler;

import com.netty.imchat.command.manager.ServerCmdHandlerManager;
import com.netty.imchat.common.entity.packet.Packet;
import com.netty.imchat.util.constant.HttpStatus;
import com.netty.imchat.util.exception.AppException;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author Kevin
 * @Title: AbstractClientCommandHandler
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/30 16:22
 */
public abstract class AbstractServerCmdHandler {
    /**
     * 获得码值
     * @return
     */
    public abstract Byte getCode();

    public AbstractServerCmdHandler(){
        ServerCmdHandlerManager.register(getCode(), this);
    }

    /**
     * 执行方法
     */
    public final void execute(ChannelHandlerContext ctx, Object msg, Packet packet){
        //1.before
        before();

        //2.主要逻辑
        executeCust(ctx, msg, packet);

        //3.after
        after();
    }

    protected void after(){
        //null
    }


    protected void before(){
        //null
    }

    protected abstract void executeCust(ChannelHandlerContext ctx, Object msg, Packet packet);

}
