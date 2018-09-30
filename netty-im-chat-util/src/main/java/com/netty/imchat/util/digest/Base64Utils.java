package com.netty.imchat.util.digest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
 * @author Kevin
 * @Title: aa
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/30 10:56
 */


public class Base64Utils {
    public static String encode(byte[] input){
        Object retObj= null;
        try {
            Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
            Method mainMethod= clazz.getMethod("encode", byte[].class);
            mainMethod.setAccessible(true);
            retObj = mainMethod.invoke(null, new Object[]{input});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return (String)retObj;
    }
    /***
     * decode by Base64
     */
    public static byte[] decode(String input) {
        Object retObj= null;
        try {
            Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
            Method mainMethod= clazz.getMethod("decode", String.class);
            mainMethod.setAccessible(true);
            retObj = mainMethod.invoke(null, input);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return (byte[])retObj;
    }
}
