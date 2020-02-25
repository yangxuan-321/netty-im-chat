package com.netty.imchat.common.disruptor.util;

import java.util.concurrent.*;

/**
 * @author : MODA-Master
 * @Title : ThreadPoolUtils
 * @ProjectName chiefdata-platform
 * @Description : TODO
 * @Time : Created in 2020/2/13 0:20
 * @Modifyed By :
 */
public class ThreadPoolUtils {
    public static final ThreadPoolExecutor newThreadExecutor(final String threadName){
        // 自定义线程池
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(),  // 核心线程数
                Runtime.getRuntime().availableProcessors() * 2, // 最大线程数
                Integer.MAX_VALUE, // 生命时间
                TimeUnit.SECONDS,   // 时间单位
                new ArrayBlockingQueue<>(20480),   // 有界队列[长度]
                new ThreadFactory() {                   // 线程工厂
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName(threadName);
                        if (thread.isDaemon()) {
                            thread.setDaemon(false);
                        }

                        if (Thread.NORM_PRIORITY != thread.getPriority()) {
                            thread.setPriority(Thread.NORM_PRIORITY);
                        }

                        return thread;
                    }
                },
                new RejectedExecutionHandler() {    // 拒绝策略
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        // 例如可以打印 哪个线程 被拒绝了
                        /**
                         * 具体就是说,当线程被使用完了且队列也满了,新来的线程任务就要被抛弃了。
                         * 抛弃要做什么处理,就是拒绝策略要做的事情
                         * 相当于一个兜底
                         */
                        System.out.println(r + "写拒绝策略");
                    }
                }
        );

        return pool;
    }

}
