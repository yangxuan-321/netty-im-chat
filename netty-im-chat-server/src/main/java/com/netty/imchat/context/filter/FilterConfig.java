package com.netty.imchat.context.filter;

import java.util.Enumeration;

/**
 * @author : Kevin
 * @Title : FilterConfig
 * @ProjectName netty-im-chat
 * @Description : TODO
 * @Time : Created in 2019/2/27 2:30
 * @Modifyed By :
 */
public interface FilterConfig {
    public String getFilterName();

    public String getInitParameter(String paramString);

    public Enumeration getInitParameterNames();
}
