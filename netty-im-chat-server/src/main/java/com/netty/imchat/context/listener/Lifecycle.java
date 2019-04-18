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
package com.netty.imchat.context.listener;


/**
 * ============================代码借鉴自 tomcat 源码===============================
 *
 * Lifecycle，其实就是一个状态机，对组件的由生到死状态的管理。
 *
 * 当组件在STARTING_PREP、STARTING或STARTED时，调用start()方法没有任何效果
 *  当组件在NEW状态时，调用start()方法会导致init()方法被立刻执行，随后start()方法被执行
 *  当组件在STOPPING_PREP、STOPPING或STOPPED时，调用stop()方法没有任何效果
 *  当一个组件在NEW状态时，调用stop()方法会将组件状态变更为STOPPED，比较典型的场景就是组件启动失败，其子组件还没有启动。当一个组件停止的时候，它将尝试停止它下面的所有子组件，即使子组件还没有启动。
 *
 *            start()
 *  -----------------------------
 *  |                           |
 *  | init()                    |
 * NEW -»-- INITIALIZING        |
 * | |           |              |     ------------------«-----------------------
 * | |           |auto          |     |                                        |
 * | |          \|/    start() \|/   \|/     auto          auto         stop() |
 * | |      INITIALIZED --»-- STARTING_PREP --»- STARTING --»- STARTED --»---  |
 * | |         |                                                            |  |
 * | |destroy()|                                                            |  |
 * | --»-----«--    ------------------------«--------------------------------  ^
 * |     |          |                                                          |
 * |     |         \|/          auto                 auto              start() |
 * |     |     STOPPING_PREP ----»---- STOPPING ------»----- STOPPED -----»-----
 * |    \|/                               ^                     |  ^
 * |     |               stop()           |                     |  |
 * |     |       --------------------------                     |  |
 * |     |       |                                              |  |
 * |     |       |    destroy()                       destroy() |  |
 * |     |    FAILED ----»------ DESTROYING ---«-----------------  |
 * |     |                        ^     |                          |
 * |     |     destroy()          |     |auto                      |
 * |     --------»-----------------    \|/                         |
 * |                                 DESTROYED                     |
 * |                                                               |
 * |                            stop()                             |
 * ---»------------------------------»------------------------------
 */
public interface Lifecycle {


    // ----------------------------------------------------- Manifest Constants


    /**
     * The LifecycleEvent type for the "component after init" event.
     */
    public static final String BEFORE_INIT_EVENT = "before_init";


    /**
     * The LifecycleEvent type for the "component after init" event.
     */
    public static final String AFTER_INIT_EVENT = "after_init";


    /**
     * The LifecycleEvent type for the "component start" event.
     */
    public static final String START_EVENT = "start";


    /**
     * The LifecycleEvent type for the "component before start" event.
     */
    public static final String BEFORE_START_EVENT = "before_start";


    /**
     * The LifecycleEvent type for the "component after start" event.
     */
    public static final String AFTER_START_EVENT = "after_start";


    /**
     * The LifecycleEvent type for the "component stop" event.
     */
    public static final String STOP_EVENT = "stop";


    /**
     * The LifecycleEvent type for the "component before stop" event.
     */
    public static final String BEFORE_STOP_EVENT = "before_stop";


    /**
     * The LifecycleEvent type for the "component after stop" event.
     */
    public static final String AFTER_STOP_EVENT = "after_stop";


    /**
     * The LifecycleEvent type for the "component after destroy" event.
     */
    public static final String AFTER_DESTROY_EVENT = "after_destroy";


    /**
     * The LifecycleEvent type for the "component before destroy" event.
     */
    public static final String BEFORE_DESTROY_EVENT = "before_destroy";


    /**
     * The LifecycleEvent type for the "periodic" event.
     */
    public static final String PERIODIC_EVENT = "periodic";


    /**
     * The LifecycleEvent type for the "configure_start" event. Used by those
     * components that use a separate component to perform configuration and
     * need to signal when configuration should be performed - usually after
     * {@link #BEFORE_START_EVENT} and before {@link #START_EVENT}.
     */
    public static final String CONFIGURE_START_EVENT = "configure_start";


    /**
     * The LifecycleEvent type for the "configure_stop" event. Used by those
     * components that use a separate component to perform configuration and
     * need to signal when de-configuration should be performed - usually after
     * {@link #STOP_EVENT} and before {@link #AFTER_STOP_EVENT}.
     */
    public static final String CONFIGURE_STOP_EVENT = "configure_stop";


    // --------------------------------------------------------- Public Methods


    /**
     * 添加监听器
     */
    public void addLifecycleListener(LifecycleListener listener);


    // 获取所以监听器
    public LifecycleListener[] findLifecycleListeners();


    // 移除某个监听器
    public void removeLifecycleListener(LifecycleListener listener);


    // 初始化方法
    public void init() throws LifecycleException;

    // 启动方法
    public void start() throws LifecycleException;


    // 停止方法，和start对应
    public void stop() throws LifecycleException;

    // 销毁方法，和init对应
    public void destroy() throws LifecycleException;


    // 获取生命周期状态
    public LifecycleState getState();


    // 获取字符串类型的生命周期状态
    public String getStateName();


    public interface SingleUse {
    }
}
