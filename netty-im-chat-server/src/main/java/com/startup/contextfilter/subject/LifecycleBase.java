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
import com.startup.contextfilter.listener.LifecycleListener;
import com.startup.contextfilter.listener.LifecycleState;
import com.startup.contextfilter.listener.LifecycleSupport;
import com.startup.init.InitListenerAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * Lifecycle的基本实现
 * 还是抽象的主题
 */
public abstract class LifecycleBase implements Lifecycle {

    private static final Logger log = LoggerFactory.getLogger(LifecycleBase.class);

    private LifecycleSupport lifecycle = new LifecycleSupport(this);

    // 初始化为 新建 状态
    private volatile LifecycleState state = LifecycleState.NEW;

    public LifecycleBase(){
        if (CollectionUtils.isEmpty(InitListenerAnnotation.subjectMap)){
            return;
        }
        lifecycle.addLifecycleListeners(InitListenerAnnotation.subjectMap.get(subjectName()));
    }

    @Override
    public void addLifecycleListener(LifecycleListener listener) {
        lifecycle.addLifecycleListener(listener);
    }

    @Override
    public List<LifecycleListener> findLifecycleListeners() {
        return lifecycle.findLifecycleListeners();
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {
        lifecycle.removeLifecycleListener(listener);
    }


    protected void fireLifecycleEvent(String type, Object data) {
        lifecycle.fireLifecycleEvent(type, data);
    }


    // 初始化 触发的方法
    // 模板方法
    @Override
    public final synchronized void init() throws LifecycleException {
        if (!state.equals(LifecycleState.STARTING)) {
//            throw new LifecycleException("此时应用未处于开始进行中的状态");
            log.info("此时应用未处于开始进行中的状态");
            return;
        }

        try {
            setStateInternal(LifecycleState.INITING, null);
            initInternal();
            setStateInternal(LifecycleState.INIT_SUCCESS, null);
        } catch (Throwable t) {
            setStateInternal(LifecycleState.INIT_FAIL, null);
            throw new LifecycleException("引用初始化失败", t);
        }
    }

    // 具体每个 初始化的实际流程
    protected abstract void initInternal() throws LifecycleException;

    @Override
    public final synchronized void start() throws LifecycleException {

        if (!state.equals(LifecycleState.NEW)) {
            return;
        }

        try {
            setStateInternal(LifecycleState.STARTING, null);
            startInternal();
//            setStateInternal(LifecycleState.START_SUCCESS, null);
        } catch (Throwable t) {
            setStateInternal(LifecycleState.START_FAIL, null);
            throw new LifecycleException("start失败", t);
        }
    }

    protected abstract void startInternal() throws LifecycleException;

    @Override
    public final synchronized void stop() throws LifecycleException {

        try {
            setStateInternal(LifecycleState.STOPING, null);
            stopInternal();
//            setStateInternal(LifecycleState.START_SUCCESS, null);
        } catch (Throwable t) {
            setStateInternal(LifecycleState.START_FAIL, null);
            throw new LifecycleException("start失败", t);
        }
    }


    protected abstract void stopInternal() throws LifecycleException;


    @Override
    public final synchronized void destroy() throws LifecycleException {
        //
    }


    protected abstract void destroyInternal() throws LifecycleException;

    @Override
    public LifecycleState getState() {
        return state;
    }


    @Override
    public String getStateName() {
        return getState().toString();
    }


    protected synchronized void setState(LifecycleState state)
            throws LifecycleException {
        setStateInternal(state, null);
    }


    protected synchronized void setState(LifecycleState state, Object data)
            throws LifecycleException {
        setStateInternal(state, data);
    }

    private synchronized void setStateInternal(LifecycleState state, Object data) throws LifecycleException {
        this.state = state;
        String lifecycleEvent = state.getLifecycleEvent();
        if (lifecycleEvent != null) {
            fireLifecycleEvent(lifecycleEvent, data);
        }
    }

    protected abstract String subjectName();
}
