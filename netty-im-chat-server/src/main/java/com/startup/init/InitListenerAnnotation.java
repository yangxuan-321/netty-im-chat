package com.startup.init;

import com.startup.context.annotation.Listener;
import com.startup.context.listener.LifecycleListener;
import com.startup.context.subject.Lifecycle;
import com.startup.context.subject.StandServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import java.util.*;

/**
 * @author : Kevin
 * @Title : InitListenerAnnotation
 * @ProjectName netty-im-chat
 * @Description : TODO
 * @Time : Created in 2019/11/6 23:09
 * @Modifyed By :
 */
public class InitListenerAnnotation implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(InitListenerAnnotation.class);
    public static Map<String, List<LifecycleListener>> subjectMap = new HashMap<String, List<LifecycleListener>>();

    static {
        try {
            ServiceLoader<LifecycleListener> loader = ServiceLoader.load(LifecycleListener.class);
            Iterator<LifecycleListener> iterator = loader.iterator();
            while (iterator.hasNext()){
                LifecycleListener listener = iterator.next();
                Listener annotation = listener.getClass().getAnnotation(Listener.class);
                if (null == annotation){
                    continue;
                }
                LifecycleListener instance = listener.getClass().newInstance();
                String subject = annotation.subject();
                if (!subjectMap.containsKey(subject)){
                    List<LifecycleListener> lifecycleListeners = new ArrayList<LifecycleListener>();
                    lifecycleListeners.add(instance);
                    subjectMap.put(subject, lifecycleListeners);
                }else {
                    subjectMap.get(subject).add(instance);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("application is starting");
        Lifecycle standServer = new StandServer();
        standServer.init();
    }
}
