package org.example.labsystem.exception;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.example.labsystem.pojo.common.CodeState;
import org.example.labsystem.pojo.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = Exception.class)
    public Result<?> exceptionHandler(Exception e){
        log.error("发生业务异常！原因是:",e);
        return Result.Error(CodeState.SERVER_ERROR);
    }
    @ExceptionHandler(value = ReadableException.class)
    public Result<?> exceptionReadableHandler(ReadableException e){
        log.error("发生业务异常！原因是：{}",e.getCodeState().msg);
        return Result.Error(e.codeState);
    }
    @ExceptionHandler(value = ExpiredJwtException.class)
    public Result<?> expiredException(ExpiredJwtException e){
        log.error("Jwt过期:{}",e.getMessage());
        return Result.Error(CodeState.JWT_ERROR);
    }
}
