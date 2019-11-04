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
package com.startup.context.listener_good;

import com.startup.context.subject.Lifecycle;
import org.apache.catalina.*;
import org.apache.catalina.deploy.*;
import org.apache.tomcat.util.bcel.classfile.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

/**
 * 读取配置文件的生命周期的具体监听器
 */
public class ConfigLifecycleListener implements LifecycleListener {

    private static final Logger log = LoggerFactory.getLogger(ConfigLifecycleListener.class);

    @Override
    public void lifecycleEvent(LifecycleEvent event) {

        try {
//            context = (Context) event.getLifecycle();
        } catch (ClassCastException e) {
//            log.error(sm.getString("contextConfig.cce", event.getLifecycle()), e);
            return;
        }

        if (event.getType().equals(Lifecycle.START_EVENT)) {
            //TODO
        } else if (event.getType().equals(Lifecycle.INIT_EVENT)) {

        } else if (event.getType().equals(Lifecycle.CONFIG)) {
            //初始化配置
            initConfig();
        } else if (event.getType().equals(Lifecycle.STOP_EVENT)) {
            //TODO
        } else {
            // boom
            // TODO
        }

    }

    private void initConfig() {
    }


    protected InputSource getContextSource() {
        //TODO
    }

//    protected void parseContextXml(InputSource source, ContextXml dest) {
//
//    }

}