package com.ruoyi.common.core.domain;


import lombok.Getter;

@Getter
public enum CodeState {
    LOGINERROR(5000,"账号或密码错误"),
    LOGINSUCCES(200,"登录成功"),
    SUCCESS(200,"请求成功"),//成功
    FAIL(400,"失败"),//失败
    UNAUTHORIZED(401,"未认证"),//未认证（签名错误）
    NOT_FOUND(404,"接口不存在"),//接口不存在
    INTERNAL_SERVER_ERROR(500,"服务器错误"),//服务器内部错误
    SERVER_ERROR(5000,"未知错误"),
    UPDATE_ERROR(-1,"更新失败"),
    DELETE_ERROR(-1,"删除失败"),
    JWT_ERROR(-1,"登录过期"),
    LOGIN_NOT(5000,"未登录"),
    APIError(300,"Api接口错误"),
    PARAMETERError(300,"参数错误错误"),
    INSERT_ERROR(-1,"插入失败");
    public final int code;
    public final String msg;

    CodeState(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
