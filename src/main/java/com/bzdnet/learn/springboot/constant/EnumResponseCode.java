package com.bzdnet.learn.springboot.constant;

import lombok.Getter;

public enum EnumResponseCode {

    SUCCESS(0),
    FAIL(1),
    ERROR(-1),
    CUSTOM_EXCEPTION(-10),
    VALID_EXCEPTION(-20);

    @Getter
    private int code;

    EnumResponseCode(int code) {
        this.code = code;
    }

}
