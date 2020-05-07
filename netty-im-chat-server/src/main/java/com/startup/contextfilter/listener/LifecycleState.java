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

package com.startup.contextfilter.listener;

/**
 * 生命周期的状态枚举
 */
public enum LifecycleState {
    NEW(false, null),
    STARTING(false, "STARTING"),
    START_SUCCESS(true, "START_SUCCESS"),
    START_FAIL(false, "START_FAIL"),
    INITING(false, "INITING"),
    INIT_SUCCESS(true, "INIT_SUCCESS"),
    INIT_FAIL(false, "INIT_FAIL"),
    CONFIGING(false, "CONFIGING"),
    CONFIG_SUCCESS(true, "CONFIG_SUCCESS"),
    CONFIG_FAIL(false, "CONFIG_FAIL"),
    STOPING(false, "STOPING"),
    STOP_SUCCESS(false, "STOP_SUCCESS"),
    STOP_FAIL(false, "STOP_FAIL");

    private final boolean available;
    private final String lifecycleEvent;

    private LifecycleState(boolean available, String lifecycleEvent) {
        this.available = available;
        this.lifecycleEvent = lifecycleEvent;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getLifecycleEvent() {
        return lifecycleEvent;
    }
}
