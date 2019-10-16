package com.bzdnet.learn.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@ToString
@TableName("t_user")
@Entity
@Table(name = "t_user")
@ApiModel
public class UserEntity {

    @TableId(value = "id_", type = IdType.AUTO)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_")
    @ApiModelProperty(value = "自增长的ID主键")
    private Long id;

    @NotEmpty
    @TableField("account_")
    @Column(name = "account_")
    @ApiModelProperty(value = "账户")
    private String account;

    @TableField("age_")
    @Column(name = "age_")
    @Max(120)
    @Min(0)
    @ApiModelProperty(value = "年龄")
    private int age;

    @TableField("disabled_")
    @Column(name = "disabled_")
    @ApiModelProperty(value = "禁用状态：0：未禁用，1：已禁用")
    private int disabled;

}
