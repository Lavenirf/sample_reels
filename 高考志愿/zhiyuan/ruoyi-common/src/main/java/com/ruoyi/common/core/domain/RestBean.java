package com.ruoyi.common.core.domain;

import lombok.Getter;

@Getter
public class RestBean<T> {
    private Integer code;
    private T data;
    private String msg;

    public RestBean() {
    }

    public RestBean(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg =msg;
    }
    public RestBean(Integer code,String msg) {
        this.code = code;
        this.msg =msg;
    }
    public static <T> RestBean<T> Success(T data){
        return new RestBean<>(CodeState.SUCCESS.code, data, CodeState.SUCCESS.msg);
    }
    public static <T> RestBean<T> Success(){
        return new RestBean<>(CodeState.SUCCESS.code,CodeState.SUCCESS.msg);
    }
    public static <T> RestBean<T> Success(CodeState code, T data){
        return new RestBean<>(code.getCode(),data,code.getMsg());
    }
    public static <T> RestBean<T> Success(CodeState code){
        return new RestBean<>(code.getCode(),code.getMsg());
    }
    public static <T> RestBean<T> Error(CodeState code){
        return new RestBean<>(code.getCode(),code.getMsg());
    }

    public static <T> RestBean<T> Error(Integer errorCode, String message) {
        return new RestBean<>(errorCode,message);
    }
}