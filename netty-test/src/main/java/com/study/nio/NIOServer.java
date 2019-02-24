package com.study.nio;

import com.study.bio.SocketIOServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Kevin
 * @Title: NIOServer
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/27 11:37
 */
public class NIOServer {

    private static final Logger log = LoggerFactory.getLogger(NIOServer.class);

    //单位ms
    public static final int WAIT_TIME = 1;

    //分配块儿大小
    static ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    /**
     * 1. 学校(ServerSocketChannel)
     * 2。 学校教务处（Selector）
     * 3。 老师 (ServerSocket )
     * 4。 学生 (SocketChannel)
     * 5。 员工号/学生号（SelectionKey
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        //专门负责处理连接
        Selector linkSelector = Selector.open();
        //专门负责处理消息
        Selector messageSelector = Selector.open();

        new Thread(()->{
            // 启动IO服务端
            try {
                //在服务端开启一个ServerSocketChannel
                ServerSocketChannel listenChannel = ServerSocketChannel.open();
                //创建基于NIO通道的socket连接
                ServerSocket socket = listenChannel.socket();
                //新建Socket端口的连接  绑定8000端口
                socket.bind(new InetSocketAddress(8000));
                //设置为非阻塞
                listenChannel.configureBlocking(false);
                //将NIO通道选绑定到择器, 当然绑定后分配的主键为skey   OP_ACCEPT:连接可接受操作,仅ServerSocketChannel支持
                listenChannel.register(linkSelector, SelectionKey.OP_ACCEPT);

                //请见 note 详解NIO

                while (true){
                    // 监测是否有新的连接，这里的1指的是阻塞的时间为1ms
                    // > 0 表示 有至少一个 选择器相关的事件
                    if(linkSelector.select(WAIT_TIME) > 0){
                        log.info("有新连接");
                        // 获取 选择器产生的事件
                        Set<SelectionKey> set = linkSelector.selectedKeys();
                        Iterator<SelectionKey> it = set.iterator();
                        while (it.hasNext()){
                            SelectionKey key = it.next();

                            //因为此处的 选择器 只负责 处理消息连接的 因此，未准备好的 连接 事件 我们不管。
                            if(!key.isAcceptable()){
                                //如果 不是 准备好的 连接事件 我们不关心
                                continue;
                            }

                            //处理每一个连接
                            try {
                                //通过key（报道的新生的报名编号--还不能算学生） 找到 ServerSocketChannel--学校
                                ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
                                //通过 学校 将新生 相关信息注册分配成 一个学生
                                SocketChannel socketChannel = serverSocketChannel.accept();
                                //设置为非阻塞
                                socketChannel.configureBlocking(false);
                                //将 channel(学生) 注册到 selector(学校教务处)  将设置成可读  并且让统一 交给处理消息的选择器 管理消息处理
                                socketChannel.register(messageSelector, SelectionKey.OP_READ);
                            }catch (Exception e){
                                e.printStackTrace();
                            }finally {
                                it.remove();
                            }
                        }
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();


        //处理消息
        new Thread(()->{
            try {
                while (true){
                    if(messageSelector.select(WAIT_TIME) > 0){
                        log.info("有新消息");
                        Set<SelectionKey> keys = messageSelector.selectedKeys();
                        Iterator<SelectionKey> it = keys.iterator();

                        while (it.hasNext()){
                            SelectionKey key = it.next();
                            if(key.isReadable()){
                                //读取数据
                                try {
                                    String read = read(key);
                                    log.info(read);
                                    log.info("------------------");
                                    //回一句消息我收到了
                                    write(key, "hello world");
                                }catch (Exception e){
                                    //如果出现了 异常 就将channel从selector中移除
                                    key.cancel();
                                    e.printStackTrace();
                                }finally {
                                    it.remove();//很重要  从已选键集中移除键
                                    try {
                                        key.interestOps(SelectionKey.OP_READ);
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();

    }


    public static String read(SelectionKey key) throws Exception{
            SocketChannel socketChannel = (SocketChannel)key.channel();
            byteBuffer.clear();
            //以块儿为读取单位
            socketChannel.read(byteBuffer);
            byteBuffer.flip();
            String data = Charset.defaultCharset().newDecoder().decode(byteBuffer).toString();

            return data;
    }


    public static void write(SelectionKey key, String message) throws Exception{
        SocketChannel socketChannel = (SocketChannel) key.channel();
        byteBuffer.clear();
        byteBuffer.put(message.getBytes());
        byteBuffer.flip();
        while(byteBuffer.hasRemaining()){
            socketChannel.write(byteBuffer);
        }
        log.info("回复消息:"+message);
    }
}
