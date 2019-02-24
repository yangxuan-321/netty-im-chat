package com.netty.imchat.util.general;

/**
 * @author Kevin
 * @Title: StringUtils
 * @ProjectName netty-im-chat
 * @Description: TODO
 * @date 2018/11/12 18:33
 */
public class StringUtils {
    /**
     * 判断是否为空
     * @param str
     * @return
     */
    public static final boolean isEmpty(String str) {
        return null == str || "".equals(str);
    }

    /**
     * 判断是否不为空
     * @param str
     * @return
     */
    public static final boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 获得字符串长度
     * @param str
     * @return
     */
    public static final int strLen(String str){
        return isEmpty(str) ? 0 : str.length();
    }
}
