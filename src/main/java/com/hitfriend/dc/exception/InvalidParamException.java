package com.hitfriend.dc.exception;

/**
 * 自定义用户输入异常
 * */
public class InvalidParamException extends BaseException{

    public InvalidParamException( int code,String message) {
        super( code,message);
    }
}
