package com.netty.imchat.context.filter;

/**
 * @author : Kevin
 * @Title : FilterChain
 * @ProjectName netty-im-chat
 * @Description : TODO
 * @Time : Created in 2019/2/27 2:33
 * @Modifyed By :
 */
public interface FilterChain<T> {
    public abstract void doFilter(T t);
}
