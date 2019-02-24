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
        System.out.println("公钥:"+publicKey + "\n" + "私钥:"+privateKey);
        byte[] bytes = RSAUtils.encryptByPublicKey(a.getBytes(), publicKey);
        byte[] bytes1 = RSAUtils.decryptByPrivateKey(bytes, privateKey);
        System.out.println(new String(bytes1, "UTF-8"));
    }
}
