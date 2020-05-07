package com.netty.imchat.disruptor.consumer;

import com.netty.imchat.command.handler.AbstractServerCmdHandler;
import com.netty.imchat.command.manager.ServerCmdHandlerManager;
import com.netty.imchat.common.disruptor.MessageConmuser;
import com.netty.imchat.common.disruptor.event.PacketEvent;

/**
 * @author : MODA-Master
 * @Title : ServerMessageConsumer
 * @ProjectName disruptor-netty
 * @Description : TODO
 * @Time : Created in 2020/2/24 21:27
 * @Modifyed By :
 */
public class ServerMessageConsumer extends MessageConmuser {

    public ServerMessageConsumer(){

    }

    public ServerMessageConsumer(String consumerId){
        this.consumerId = consumerId;
    }

    @Override
    public void onEvent(PacketEvent event) throws Exception {
        AbstractServerCmdHandler cmdHandler = ServerCmdHandlerManager.getClientCmdHandler(event.getPacket().getCommand());
        cmdHandler.execute(event.getCtx(), event.getMsg(), event.getPacket());
    }
}
