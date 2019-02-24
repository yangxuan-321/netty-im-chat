package com.study.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Kevin
 * @Title: SocketIOServer
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/26 18:58
 */
public class SocketIOServer {
    private static final Logger log = LoggerFactory.getLogger(SocketIOServer.class);
    public static void main(String[] args){
        log.info("主线程开始");
        try{
            ServerSocket socket = new ServerSocket(8000);
            new Thread(()->{
                while(true){
                    try {
                        Socket accept = socket.accept();
                        new Thread(()->{
                            BufferedReader br = null;
                            try{
                                log.info("------有连接进来了---------");
                                InputStream inputStream = accept.getInputStream();
                                InputStreamReader isr = new InputStreamReader(inputStream);
                                br = new BufferedReader(isr);
                                String str = "";
                                while (true){
                                    //Socket输入流在使用 read读取时，如果无数据 会阻塞知道有数据发生（https://blog.csdn.net/fw0124/article/details/41227543）
                                    //知道 socket或者另一端的流被关闭时 才会返回null 或者 -1
                                    str = br.readLine();
                                    log.info(str);
                                }
                                //System.out.println("停止了");
                            }catch (Exception e){
                                e.printStackTrace();
                            }finally {
                                if(null != br){
                                    try {
                                        br.close();
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                                if(null != accept){
                                    try {
                                        accept.close();
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }).start();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }catch (Exception e){
            e.printStackTrace();
        }
        while (true){
            if(1 == 0){
                break;
            }
        }
        log.info("主线程结束");
        //结束程序
        System.exit(0);
    }
}
