package com.bzdnet.learn.springboot.handler;

import com.bzdnet.learn.springboot.constant.EnumResponseCode;
import com.bzdnet.learn.springboot.exception.CustomException;
import com.bzdnet.learn.springboot.vo.ResultVO;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResultVO handleCustomException(CustomException ce) {
        return new ResultVO(EnumResponseCode.CUSTOM_EXCEPTION.getCode(), ce.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public ResultVO handleError(BindException be) {
        return new ResultVO(EnumResponseCode.VALID_EXCEPTION.getCode(), be.getMessage());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResultVO handleValidationParamError(ConstraintViolationException ex) {
        ex.printStackTrace();
        return new ResultVO(EnumResponseCode.VALID_EXCEPTION.getCode(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultVO handleGlobalError(Exception ex) {
        ex.printStackTrace();
        return new ResultVO(EnumResponseCode.ERROR.getCode(), ex.getMessage());
    }

}
