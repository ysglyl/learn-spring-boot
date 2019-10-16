package com.bzdnet.learn.springboot.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(description = "用户表单")
public class UserForm extends PagedForm {

    @ApiModelProperty(value = "用户ID")
    private Long id;
    @ApiModelProperty(value = "用户禁用状态")
    private int disabled;

}
