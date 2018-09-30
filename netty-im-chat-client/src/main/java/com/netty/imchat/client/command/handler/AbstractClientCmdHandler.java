package com.netty.imchat.client.command.handler;

import com.netty.imchat.client.command.manager.ClientCmdHandlerManager;
import com.netty.imchat.common.entity.packet.Packet;
import com.netty.imchat.common.util.PacketCodeUtil;
import com.netty.imchat.util.exception.AppException;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author Kevin
 * @Title: AbstractClientCommandHandler
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/30 16:22
 */
public abstract class AbstractClientCmdHandler {
    private String publicKey;

    private Bootstrap boot;

    protected String getPublicKey() {
        return publicKey;
    }

    protected void setPublicKey(String publicKey) {
        throw new AppException("不能写入pk");//不能写入pk
    }

    protected Bootstrap getBoot() {
        return boot;
    }

    public void setBoot(Bootstrap boot) {
        this.boot = boot;
    }

    /**
     * 获得码值
     * @return
     */
    public abstract Byte getCode();

    public AbstractClientCmdHandler(){
        ClientCmdHandlerManager.register(getCode(), this);
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
