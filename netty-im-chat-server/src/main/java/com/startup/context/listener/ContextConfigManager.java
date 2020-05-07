package com.startup.context.listener;

import com.startup.context.pojo.FilterDescribe;

import java.util.ArrayList;
import java.util.List;

/**
 * 对配置文件的管理器
 */
public interface ContextConfigManager {
    public static final List<FilterDescribe> filters = new ArrayList<FilterDescribe>();
}
