package com.bzdnet.learn.springboot.exception;

import lombok.Data;

@Data
public class CustomException extends Exception {

    private String message;

    public CustomException() {
    }

    public CustomException(String message) {
        this.message = message;
    }
}
