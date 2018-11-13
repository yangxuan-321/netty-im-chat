package com.netty.imchat.command.manager;

import com.netty.imchat.command.handler.AbstractServerCmdHandler;
import com.netty.imchat.util.exception.AppException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kevin
 * @Title: ServerCmdHandlerManager
 * @ProjectName netty-im-chat
 * @Description: TODO
 * @date 2018/11/13 11:02
 */
public class ServerCmdHandlerManager {
    private static final Map<Byte, AbstractServerCmdHandler> CMD_HANDLERS = new HashMap<Byte, AbstractServerCmdHandler>();

    /**
     * 注册
     * @param code
     * @param serverCmdHandler
     * @return
     */
    public static boolean register(Byte code, AbstractServerCmdHandler serverCmdHandler){
        if(null == code || null == serverCmdHandler){
            throw new AppException("注册失败"); //注册失败
        }

        AbstractServerCmdHandler old = CMD_HANDLERS.get(code);
        if(null != old){
            return false;
        }

        CMD_HANDLERS.put(code, serverCmdHandler);
        return null != CMD_HANDLERS.get(code);
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

        CMD_HANDLERS.remove(code);
        return null == CMD_HANDLERS.get(code);
    }

    public static AbstractServerCmdHandler getClientCmdHandler(Byte code){
        if(null == code){
            throw new AppException("code不能为空"); //code不能为空
        }

        return CMD_HANDLERS.get(code);
    }
}
