package com.bzdnet.learn.springboot.controller;

import com.bzdnet.learn.springboot.constant.EnumResponseCode;
import com.bzdnet.learn.springboot.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected <T> Result<T> getSuccess() {
        return getSuccess(null);
    }

    protected <T> Result<T> getSuccess(T data) {
        return new Result<>(EnumResponseCode.SUCCESS.getCode(), "success", data);
    }

    protected Result getFail() {
        return new Result(EnumResponseCode.FAIL.getCode(), "fail");
    }

}
