package com.netty.imchat.util.exception;

/**
 * @author Kevin
 * @Title: AppException
 * @ProjectName studyjava
 * @Description: TODO 异常类
 * @date 2018/9/29 20:35
 */
public class AppException extends RuntimeException{
    private int code;

    private String message;

    private Throwable throwable;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public AppException(){
        super();
    }

    public AppException(String message){
        this.message = message;
    }

    public AppException(int code, String message){
        this.code = code;
        this.message = message;
    }

}
