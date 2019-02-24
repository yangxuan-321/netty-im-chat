package com.netty.imchat.run;

import com.netty.imchat.constant.Constants;
import com.netty.imchat.util.digest.Base64Utils;
import com.netty.imchat.util.digest.RSAUtils;

import java.util.Map;

/**
 * @author Kevin
 * @Title: TestRSA
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/30 11:23
 */
public class TestRSA {
    public static void main(String[] args) throws Exception{
        String a = "55555";

        Map<String, Object> stringObjectMap = RSAUtils.genKeyPair();
        String publicKey = RSAUtils.getPublicKey(stringObjectMap);
        String privateKey = RSAUtils.getPrivateKey(stringObjectMap);
        System.out.println(publicKey);
        System.out.println(privateKey);
        String decode = Base64Utils.encode(a.getBytes());
        byte[] jiami = RSAUtils.encryptByPublicKey(decode.getBytes(), publicKey);
        byte[] jiemi = RSAUtils.decryptByPrivateKey(jiami, privateKey);
        byte[] decode1 = Base64Utils.decode(new String(jiemi));
        System.out.println(new String(decode1));
    }
}
