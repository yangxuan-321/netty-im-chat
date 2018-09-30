package com.netty.imchat.util.time;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Kevin
 * @Title: DateTimeUtil
 * @ProjectName studyjava
 * @Description: TODO
 * @date 2018/9/29 16:52
 */
public class DateTimeUtil {
    /**
     * 获取当前时间
     * @return
     */
    public static final String getNowTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
