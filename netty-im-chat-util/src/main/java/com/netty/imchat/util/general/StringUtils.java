package com.netty.imchat.util.general;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    /**
     * 校验一个字符是否是汉字
     *
     * @param c
     *            被校验的字符
     * @return true代表是汉字
     */
    public static boolean isChineseChar(char c) {
        try {
            return String.valueOf(c).getBytes("UTF-8").length > 1;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 验证字符串内容是否包含下列非法字符<br>
     * `~!#%^&*=+\\|{};:'\",<>/?○●★☆☉♀♂※¤╬の〆
     *
     * @param content
     *            字符串内容
     * @return 'true 代表不包含非法字符，false代表包含非法字符。
     */
    public static boolean validateLegalString(String content) {
        String illegal = "`~!#%^&*=+\\|{};:'\",<>/?○●★☆☉♀♂※¤╬の〆";
        for (int i = 0; i < content.length(); i++) {
            String s = content.charAt(i) + "";
            if (illegal.contains(s)){
                return false;
            }
        }
        return true;
    }

    /**
     * 验证是否是汉字或者0-9、a-z、A-Z
     *
     * @param c
     *            被验证的char
     * @return true代表符合条件
     */
    public static boolean isRightChar(char c) {
        return isChinese(c) || isWord(c);
    }

    /**
     * 校验某个字符是否是a-z、A-Z、_、0-9
     *
     * @param c
     *            被校验的字符
     * @return true代表符合条件
     */
    public static boolean isWord(char c) {
        String regEx = "[\\w]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher("" + c);
        return m.matches();
    }

    /**
     * 判定输入的是否是汉字
     *
     * @param c
     *            被校验的字符
     * @return true代表是汉字
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 校验String是否全是中文
     *
     * @param name
     *            被校验的字符串
     * @return true代表全是汉字
     */
    public static boolean checkNameChese(String name) {
        boolean res = true;
        char[] cTemp = name.toCharArray();
        for (int i = 0; i < name.length(); i++) {
            if (!isChinese(cTemp[i])) {
                res = false;
                break;
            }
        }
        return res;
    }
}
