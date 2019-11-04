package com.startup.filter;

/**
 * @author : Kevin
 * @Title : Filter
 * @ProjectName netty-im-chat
 * @Description : TODO 过滤器
 * @Time : Created in 2019/2/27 2:27
 * @Modifyed By :
 */
public interface Filter<T> {
    public void init(FilterConfig paramFilterConfig);

    public void doFilter(T t, FilterChain filterChain);

    public void destroy();
}
