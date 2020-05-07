package com.netty.imchat.common.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.ProducerType;
import com.netty.imchat.common.disruptor.event.PacketEvent;
import com.netty.imchat.common.disruptor.exception.EventExceptionHandler;
import com.netty.imchat.common.disruptor.util.ThreadPoolUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : MODA-Master
 * @Title : RingBufferWorkerPoolFactory
 * @ProjectName disruptor-netty
 * @Description : TODO
 * @Time : Created in 2020/2/24 17:53
 * @Modifyed By :
 */
public class RingBufferWorkerPoolFactory {
    private static final class SingletonHolder{
        static final RingBufferWorkerPoolFactory instance = new RingBufferWorkerPoolFactory();
    }

    private RingBufferWorkerPoolFactory(){

    }

    public static RingBufferWorkerPoolFactory getInstance(){
        return SingletonHolder.instance;
    }

    // 生产者池
    private static final Map<String, MessageProducer> producers = new ConcurrentHashMap<String, MessageProducer>();
    // 消费者池
    private static final Map<String, MessageConmuser> conmusers = new ConcurrentHashMap<String, MessageConmuser>();

    private RingBuffer<PacketEvent> ringBuffer;

    private SequenceBarrier sequenceBarrier;

    private WorkerPool<PacketEvent> workerPool;

    public void initAndStart(ProducerType type, int bufferSize, WaitStrategy waitStrategy, MessageConmuser[] messageConmusers){
        // 1.构建 RingBuffer 对象
        this.ringBuffer = RingBuffer.create(type, new EventFactory<PacketEvent>() {
            @Override
            public PacketEvent newInstance() {
                return new PacketEvent();
            }
        }, bufferSize, waitStrategy);

        // 2.设置序号栅栏
        this.sequenceBarrier = this.ringBuffer.newBarrier();

        // 3.设置工作池
        this.workerPool = new WorkerPool<PacketEvent>(this.ringBuffer, this.sequenceBarrier,
                new EventExceptionHandler(), messageConmusers);

        // 4.把所构建的消费者 放入 池中
        for (MessageConmuser mc: messageConmusers) {
            RingBufferWorkerPoolFactory.conmusers.put(mc.getConsumerId(), mc);
        }

        // 5.添加我们的sequence
        this.ringBuffer.addGatingSequences(this.workerPool.getWorkerSequences());

        // 6.启动我们的工作池
        this.workerPool.start(ThreadPoolUtils.newThreadExecutor("disruptor"));
    }

    public MessageProducer getMessageProducer(String producerId){
        MessageProducer producer = producers.get(producerId);
        if (null == producer){
            producer = new MessageProducer(producerId, this.ringBuffer);
            producers.put(producerId, producer);
        }

        return producer;
    }
}
