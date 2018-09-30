package com.study.bio;

import java.io.*;
import java.net.Socket;

/**
 * @author Kevin
 * @Title: SocketIOClient
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/26 19:40
 */
public class SocketIOClient {
    public static void main(String[] args) throws Exception{
        new Thread(()->{
            int i = 0;
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                OutputStream outputStream = socket.getOutputStream();
                while (true){
                    OutputStreamWriter osw = new OutputStreamWriter(outputStream);
                    PrintWriter pw = new PrintWriter(osw);
                    pw.println("hello world");
                    pw.flush();
                    Thread.sleep(2000);
                    if((i++) > 5){
                        pw.print((String)null);
//                        socket.close();
                        pw.close();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }
}
