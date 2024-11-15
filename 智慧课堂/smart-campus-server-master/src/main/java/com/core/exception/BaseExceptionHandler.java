package com.core.exception;

import com.core.utils.result.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常拦截
 */
@ControllerAdvice
public class BaseExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);

    /**
     * 业务异常拦截处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public AjaxResult serviceExceptionHandler(ServiceException exception) {
        logger.error("业务异常拦截处理", exception);
        if (exception.getCode() != null) {
            return AjaxResult.error(exception.getCode(), exception.getMessage());
        }
        return AjaxResult.error(exception.getMessage());
    }

    /**
     * 其他的异常拦截处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public AjaxResult handleException(Exception exception){
        logger.error("其他的异常拦截处理", exception);
        return AjaxResult.error("系统异常");
    }

}
