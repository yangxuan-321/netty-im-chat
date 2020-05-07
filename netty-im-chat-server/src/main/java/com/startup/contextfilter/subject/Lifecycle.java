/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.startup.contextfilter.subject;


import com.startup.contextfilter.listener.LifecycleException;
import com.startup.contextfilter.listener.LifecycleState;
import com.startup.contextfilter.listener.LifecycleListener;

import java.util.List;

/**
 *  Lifecycle 是主题 (主题接口)
 * ============================代码借鉴自 tomcat 源码===============================
 *
 * Lifecycle，其实就是一个状态机，对组件的由生到死状态的管理。
 *
 */
public interface Lifecycle {

    public static final String START_EVENT = "start";

    public static final String INIT_EVENT = "init";

    public static final String CONFIG = "config";

    public static final String STOP_EVENT = "stop";



/**
 * -------------------------------定义3个管理监听器的方法--------------------------------------
 */
    // 添加某个监听器
    public void addLifecycleListener(LifecycleListener listener);


    // 获取所有监听器
    public List<LifecycleListener> findLifecycleListeners();


    // 移除某个监听器
    public void removeLifecycleListener(LifecycleListener listener);
/**
 * -------------------------------定义3个管理监听器的方法--------------------------------------
 */


/**
 * -------------------------------定义4个生命周期的方法--------------------------------------
 */
    // 初始化方法
    public void init() throws LifecycleException;

    // 启动方法
    public void start() throws LifecycleException;


    // 停止方法，和start对应
    public void stop() throws LifecycleException;

    // 销毁方法，和init对应
    public void destroy() throws LifecycleException;
/**
 * -------------------------------定义4个生命周期的方法--------------------------------------
 */


/**
 * -------------------------------定义2个获取当前状态的方法--------------------------------------
 */
    // 获取生命周期状态
    public LifecycleState getState();


    // 获取字符串类型的生命周期状态
    public String getStateName();
/**
 * -------------------------------定义2个获取当前状态的方法--------------------------------------
 */

    public interface SingleUse {
    }
}
