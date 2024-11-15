package com.core.utils.result;

import com.core.constant.CommonConstant;
import com.core.constant.HttpStatus;
import lombok.Data;

/**
 * 返回结果
 */
@Data
public class AjaxResult {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回提示
     */
    private String msg;

    /**
     * 返回消息级别
     */
    private String level;

    /**
     * 数据对象
     */
    private Object data;

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    private AjaxResult() {
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code  状态码
     * @param msg   返回提示
     * @param level 返回消息级别
     * @param data  数据对象
     */
    private AjaxResult(int code, String msg, String level, Object data) {
        this.code = code;
        this.msg = msg;
        this.level = level;
        if (data != null) {
            this.data = data;
        }
    }


    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static AjaxResult success() {
        return AjaxResult.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static AjaxResult success(Object data) {
        return AjaxResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回提示
     * @return 成功消息
     */
    public static AjaxResult success(String msg) {
        return AjaxResult.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回提示
     * @param data 数据对象
     * @return 成功消息
     */
    public static AjaxResult success(String msg, Object data) {
        return new AjaxResult(HttpStatus.SUCCESS, msg, CommonConstant.SUCCESS_LEVEL, data);
    }

    /**
     * 返回警告消息
     *
     * @param msg 返回提示
     * @return 警告消息
     */
    public static AjaxResult warn(String msg) {
        return AjaxResult.warn(msg, null);
    }

    /**
     * 返回警告消息
     *
     * @param msg  返回提示
     * @param data 数据对象
     * @return 警告消息
     */
    public static AjaxResult warn(String msg, Object data) {
        return new AjaxResult(HttpStatus.ERROR, msg, CommonConstant.WARN_LEVEL, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static AjaxResult error() {
        return AjaxResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回提示
     * @return 警告消息
     */
    public static AjaxResult error(String msg) {
        return AjaxResult.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回提示
     * @param data 数据对象
     * @return 警告消息
     */
    public static AjaxResult error(String msg, Object data) {
        return new AjaxResult(HttpStatus.ERROR, msg, CommonConstant.ERROR_LEVEL, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回提示
     * @return 警告消息
     */
    public static AjaxResult error(int code, String msg) {
        return new AjaxResult(code, msg, CommonConstant.WARN_LEVEL, null);
    }


}
