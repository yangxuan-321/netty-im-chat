package com.startup.context.subject;

import com.startup.context.listener.LifecycleException;
import com.startup.context.listener.LifecycleState;
import org.springframework.stereotype.Component;

/**
 * @author : Kevin
 * @Title : StandServer
 * @ProjectName netty-im-chat
 * @Description : TODO
 * @Time : Created in 2019/11/2 16:31
 * @Modifyed By :
 */
public class StandServer extends LifecycleBase {
    @Override
    protected void initInternal() throws LifecycleException {
        fireLifecycleEvent(START_EVENT, null);
        setState(LifecycleState.STARTING);
    }

    @Override
    protected void startInternal() throws LifecycleException {

    }

    @Override
    protected void stopInternal() throws LifecycleException {

    }

    @Override
    protected void destroyInternal() throws LifecycleException {

    }

    @Override
    protected String subjectName() {
        return "StandServer";
    }
}
