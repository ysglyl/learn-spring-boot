package com.bzdnet.learn.springboot.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResultVO<T> {

    private int code;
    private String message;
    private T data;

    public ResultVO(int code, String message) {
        this(code, message, null);
    }

    public ResultVO(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
