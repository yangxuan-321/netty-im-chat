package com.netty.imchat.client.command.receive.manager;

import com.netty.imchat.client.command.receive.handler.AbstractClientCmdHandler;
import com.netty.imchat.util.exception.AppException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kevin
 * @Title: ClientCmdHandlerManager
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/30 16:47
 */
public class ClientCmdHandlerManager {
    private static final Map<Byte, AbstractClientCmdHandler> cmdHandlers = new HashMap<Byte, AbstractClientCmdHandler>();

    /**
     * 注册
     * @param code
     * @param clientCmdHandler
     * @return
     */
    public static boolean register(Byte code, AbstractClientCmdHandler clientCmdHandler){
        if(null == code || null == clientCmdHandler){
            throw new AppException("注册失败"); //注册失败
        }

        AbstractClientCmdHandler old = cmdHandlers.get(code);
        if(null != old){
            return false;
        }

        cmdHandlers.put(code, clientCmdHandler);
        return null != cmdHandlers.get(code);
    }

    /**
     * 移除注册
     * @param code
     * @return
     */
    public static boolean remove(Byte code){
        if(null == code){
            throw new AppException("code不能为空"); //code不能为空
        }

        cmdHandlers.remove(code);
        return null == cmdHandlers.get(code);
    }

    public static AbstractClientCmdHandler getClientCmdHandler(Byte code){
        if(null == code){
            throw new AppException("code不能为空"); //code不能为空
        }

        return cmdHandlers.get(code);
    }

}
