package com.bzdnet.learn.springboot.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@ToString
public class User {

    @NotEmpty
    private String account;
    @NotEmpty
    private String password;
    @Min(0)
    @Max(120)
    private int age;

}
