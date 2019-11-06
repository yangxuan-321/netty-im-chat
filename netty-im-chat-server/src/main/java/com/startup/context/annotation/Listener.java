package com.startup.context.annotation;

/**
 * @author Kevin
 * @Title: Listener
 * @ProjectName netty-im-chat
 * @Description: TODO
 * @date 2019/11/6 10:54
 */
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Listener {

    String name() default "";

    String subject() default "";

}
