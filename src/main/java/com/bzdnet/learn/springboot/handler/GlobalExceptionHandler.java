package com.bzdnet.learn.springboot.handler;

import com.bzdnet.learn.springboot.constant.EnumResponseCode;
import com.bzdnet.learn.springboot.exception.CustomException;
import com.bzdnet.learn.springboot.result.Result;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public Result handleCustomException(CustomException ce) {
        return new Result(EnumResponseCode.CUSTOM_EXCEPTION.getCode(), ce.getMessage());
    }

    @ExceptionHandler({BindException.class, ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public Result handleValidException(Exception ex) {
        return new Result(EnumResponseCode.VALID_EXCEPTION.getCode(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result handleGlobalError(Exception ex) {
        ex.printStackTrace();
        return new Result(EnumResponseCode.ERROR.getCode(), ex.getMessage());
    }

}
