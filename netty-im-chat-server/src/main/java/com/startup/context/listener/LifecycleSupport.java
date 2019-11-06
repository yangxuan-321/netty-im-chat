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


package com.startup.context.listener;

import com.startup.context.subject.Lifecycle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 工具类来管理对组件注册的监听器
 * LifecycleSupport类中用数组类型的变量listeners存储了所有监听器。当添加一个新的监听器时，是创建一个新数组，存储全部的监听器。删除一个监听器的时候，也是返回一个新的数组对象。
 *
 * fireLifecycleEvent方法会触发已经注册的各个监听器。
 *
 * 实现了Lifecycle接口的组件可以使用LifecycleSupport类，对监听器进行添加、删除、触发等操作。
 *
 * @author Craig R. McClanahan
 */
public final class LifecycleSupport {

    private Lifecycle lifecycle = null;

    public LifecycleSupport(Lifecycle lifecycle) {
        super();
        this.lifecycle = lifecycle;

    }

    /**
     * 对于注册的生命周期监听器用于存放的集合
     */
    private final List<LifecycleListener> listeners = new ArrayList<LifecycleListener>();

    // 对象锁，用于防止启动的时候，造成集合（listeners）操作的线程不安全问题
    private final Object listenersLock = new Object();

    /**
     * 增加监听器
     */
    public void addLifecycleListener(LifecycleListener listener) {
        synchronized (listenersLock) {
            listeners.add(listener);
        }

    }

    public void addLifecycleListeners(List<LifecycleListener> listeners) {
        synchronized (listenersLock) {
            listeners.addAll(listeners);
        }

    }

    /**
     * 获取所有的监听器
     * @return
     */
    public List<LifecycleListener> findLifecycleListeners() {
        return listeners;
    }


    /**
     * 通知所有的关于生命周期的监听器,已经发生了特定事件,使用同步方式执行此通知。
     */
    public void fireLifecycleEvent(String type, Object data) {
        //通过type判断哪个状态
        LifecycleEvent event = new LifecycleEvent(lifecycle, type, data);
        for (LifecycleListener l : listeners){
            l.lifecycleEvent(event);
        }
    }


    /**
     * 移除监听器
     */
    public void removeLifecycleListener(LifecycleListener listener) {

        synchronized (listenersLock) {
            Iterator<LifecycleListener> iterator = listeners.iterator();
            while (iterator.hasNext()){
                LifecycleListener l = iterator.next();
                if (l == listener){
                    iterator.remove();
                }
            }
        }

    }

}
