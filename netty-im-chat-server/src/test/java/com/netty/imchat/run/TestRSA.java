package com.netty.imchat.run;

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
        String a = "123";

        Map<String, Object> stringObjectMap = RSAUtils.genKeyPair();
        String publicKey = RSAUtils.getPublicKey(stringObjectMap);
        String privateKey = RSAUtils.getPrivateKey(stringObjectMap);
        byte[] bytes = RSAUtils.encryptByPrivateKey(a.getBytes(), privateKey);
        byte[] bytes1 = RSAUtils.decryptByPublicKey(bytes, publicKey);
        System.out.println(new String(bytes1, "UTF-8"));
    }
}
