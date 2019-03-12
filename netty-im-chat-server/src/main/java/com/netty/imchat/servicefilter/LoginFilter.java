package com.netty.imchat.servicefilter;

import com.netty.imchat.common.entity.packet.Packet;
import com.netty.imchat.context.filter.Filter;
import com.netty.imchat.context.filter.FilterChain;
import com.netty.imchat.context.filter.FilterConfig;

/**
 * @author Kevin
 * @Title: LoginFilter
 * @ProjectName netty-im-chat
 * @Description: TODO
 * @date 2019/3/1 14:21
 */
public class LoginFilter implements Filter<Packet> {
    @Override
    public void init(FilterConfig paramFilterConfig) {

    }

    @Override
    public void doFilter(Packet packet, FilterChain filterChain) {

    }

    @Override
    public void destroy() {

    }
}
