package com.bzdnet.learn.springboot.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "通用的返回数据模型")
public class Result<T> {

    @ApiModelProperty(value = "结果码，0：正常返回，大于0：非正常返回，小于0：存在错误")
    private int code;
    @ApiModelProperty(value = "结果说明")
    private String message;
    @ApiModelProperty(value = "返回的数据泛型或泛型列表")
    private T data;

    public Result(int code, String message) {
        this(code, message, null);
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
