package com.bzdnet.learn.springboot.controller;

import com.bzdnet.learn.springboot.constant.EnumResponseCode;
import com.bzdnet.learn.springboot.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected <T> ResultVO getSuccess(T data) {
        return new ResultVO(EnumResponseCode.SUCCESS.getCode(), "success", data);
    }

    protected <T> ResultVO getSuccess() {
        return new ResultVO(EnumResponseCode.SUCCESS.getCode(), "success");
    }
}
