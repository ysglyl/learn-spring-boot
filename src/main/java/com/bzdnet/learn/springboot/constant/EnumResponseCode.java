package com.bzdnet.learn.springboot.constant;

import lombok.Getter;

public enum EnumResponseCode {

    SUCCESS(200),
    ERROR(500),
    CUSTOM_EXCEPTION(800),
    VALID_EXCEPTION(900);

    @Getter
    private int code;

    EnumResponseCode(int code) {
        this.code = code;
    }

}
