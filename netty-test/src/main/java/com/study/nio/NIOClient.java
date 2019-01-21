package com.study.nio;

import com.study.bio.SocketIOServer;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author Kevin
 * @Title: NIOClient
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/27 15:24
 */
public class NIOClient {

    private static final Logger log = LoggerFactory.getLogger(NIOClient.class);

    static ByteBuffer buffer = ByteBuffer.allocate(1024);

    static SocketChannel socketChannel = null;

    static int i=0;

    private static Selector selector;

    public static void main(String[] args) {

        try {
            //打开学生连接
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            //连接老师
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8000));

            if(socketChannel.finishConnect())
            {
                while(true)
                {
                    Thread.sleep(2000);
                    write(socketChannel);
                    String read = read(socketChannel);
                    if(StringUtils.isEmpty(read)){
                        Thread.sleep(1);
                        read = read(socketChannel);
                    }
                    log.info("收到消息:" + read);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }

    public static void write(SocketChannel socketChannel) throws Exception{
        String info = "I'm "+i+++"-th information from client";
        buffer.clear();
        buffer.put(info.getBytes());
        buffer.flip();
        while(buffer.hasRemaining()){
            log.info("发出消息:"+info);
            socketChannel.write(buffer);
        }
    }

    public static String read(SocketChannel socketChannel) throws Exception{
        buffer.clear();
        //以块儿为读取单位
        socketChannel.read(buffer);
        buffer.flip();
        String data = Charset.defaultCharset().newDecoder().decode(buffer).toString();
        return data;
    }
}
