package com.netty.imchat.run;

import com.netty.imchat.util.digest.RSAUtils;

import java.util.Map;

/**
 * @author Kevin
 * @Title: CreateRSA
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/30 11:07
 */
public class CreateRSA {
    public static void main(String[] args) throws Exception{
        Map<String, Object> stringObjectMap = RSAUtils.genKeyPair();
        System.out.println("-------------");
        System.out.println("-------------"+ RSAUtils.getPublicKey(stringObjectMap));
        System.out.println("-------------"+RSAUtils.getPrivateKey(stringObjectMap));
    }
}
