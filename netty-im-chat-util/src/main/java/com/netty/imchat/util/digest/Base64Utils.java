package com.netty.imchat.util.digest;

import java.util.Base64;

/**
 * @author Kevin
 * @Title: aa
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/30 10:56
 */


public class Base64Utils {
    public static String encode(byte[] input){
        return Base64.getEncoder().encodeToString(input);
    }

    public static byte[] decode(String input) {
        try{
            Base64.Decoder decoder = Base64.getDecoder();
            return decoder.decode(input);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
