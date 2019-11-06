package com.netty.imchat.servicefilter;

import com.netty.imchat.common.entity.packet.Packet;
import com.netty.imchat.common.enums.CommandEnum;
import com.startup.filter.Filter;
import com.startup.filter.FilterChain;
import com.startup.filter.FilterConfig;

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
        if (packet.getCommand() == CommandEnum.LOGIN_REQUEST.getCode()){
            filterChain.doFilter(packet);
        }
    }

    @Override
    public void destroy() {

    }
}
