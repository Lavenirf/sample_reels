package org.example.labsystem.pojo.common;

import lombok.Data;
import lombok.Getter;

@Getter
public class Result<T> {
    private Integer code;
    private T data;
    private String msg;

    public Result(Integer code, T data,String msg) {
        this.code = code;
        this.data = data;
        this.msg =msg;
    }
    public Result(Integer code,String msg) {
        this.code = code;
        this.msg =msg;
    }
    public static <T> Result<T> Success(CodeState code, T data){
        return new Result<T>(code.getCode(),data,code.getMsg());
    }
    public static <T> Result<T> Success(CodeState code){
        return new Result<T>(code.getCode(),code.getMsg());
    }
    public static <T> Result<T> Fail(CodeState code){
        return new Result<T>(code.getCode(),code.getMsg());
    }
    public static <T> Result<T> Error(CodeState code){
        return new Result<T>(code.getCode(),code.getMsg());
    }

    public static <T> Result<T> Error(Integer errorCode, String message) {
        return new Result<T>(errorCode,message);
    }
}
