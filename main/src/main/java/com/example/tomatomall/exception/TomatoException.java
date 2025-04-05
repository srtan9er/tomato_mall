package com.example.tomatomall.exception;

public class TomatoException extends RuntimeException {
    public TomatoException(String message) {
        super(message);
    }
    public static TomatoException phoneAlreadyExists(){
        return new TomatoException("手机号已经存在!");
    }

    public static TomatoException notLogin(){
        return new TomatoException("未登录!");
    }

    public static TomatoException phoneOrPasswordError(){
        return new TomatoException("手机号或密码错误!");
    }

    public static TomatoException nameAlreadyExists() {
        return new TomatoException("此名称已被使用!");
    }

    public static TomatoException fileUploadFail() {
        return new TomatoException("文件上传失败!");
    }

    public static TomatoException tokenillegal(){
        return new TomatoException("token核验不通过");
    }

    public static TomatoException nulltoken(){
        return new TomatoException("token是null");
    }
}
