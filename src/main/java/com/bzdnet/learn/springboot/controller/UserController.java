package com.bzdnet.learn.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bzdnet.learn.springboot.entity.UserEntity;
import com.bzdnet.learn.springboot.form.UserForm;
import com.bzdnet.learn.springboot.result.Result;
import com.bzdnet.learn.springboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/user")
@Validated
@Api(tags = {"用户相关的Rest Api接口"})
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "查询全部列表", notes = "根据用户状态查询全部的用户列表")
    @PostMapping("/allList")
    public Result allList(@RequestBody UserForm form) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<UserEntity>();
        queryWrapper.eq("disabled_", form.getDisabled());
        return getSuccess(userService.list(queryWrapper));
    }

    @ApiOperation(value = "查询分页列表", notes = "根据用户状态查询分页的用户列表")
    @PostMapping("/pageList")
    public Result pageList(@RequestBody UserForm form) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<UserEntity>();
        queryWrapper.eq("disabled_", form.getDisabled());
        Page<UserEntity> page = new Page<>(form.getCurrent(), form.getSize());
        IPage<UserEntity> pageList = userService.page(page, queryWrapper);
        return getSuccess(pageList);
    }

    @ApiOperation(value = "插入用户信息")
    @PostMapping("/add")
    public Result insert(@Validated @RequestBody UserEntity entity) {
        if (userService.save(entity)) {
            return getSuccess();
        }
        return getFail();
    }

    @ApiOperation(value = "更新用户信息")
    @PostMapping("/edit")
    public Result update(@RequestBody UserEntity entity) {
        if (userService.updateById(entity)) {
            return getSuccess();
        }
        return getFail();
    }

    @ApiOperation(value = "根据ID删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", dataType = "int", paramType = "path"),
    })
    @PostMapping("/del/{id}")
    public Result delete(@NotNull @PathVariable("id") Long id) {
        if (userService.removeById(id)) {
            return getSuccess();
        }
        return getFail();
    }

    @ApiOperation(value = "查询年龄范围内的用户列表", notes = "包含最大年龄和最小年龄")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "min", value = "最小年龄（0-120）", dataType = "int"),
            @ApiImplicitParam(name = "max", value = "最大年龄（0-120）", dataType = "int")
    })
    @PostMapping("/selectListBetweenAge")
    public Result selectListBetweenAge(@Min(0) @Max(120) @RequestParam("min") int min, @Min(0) @Max(120) @RequestParam("max") int max) {
        return getSuccess(userService.selectListBetweenAge(min, max));
    }

    /**
     * 查询全部列表
     *
     * @return
     */
    @ApiOperation(value = "查询全部列表", notes = "底层使用Jpa实现")
    @RequestMapping(value = "/findAll", method = {RequestMethod.POST})
    public Result findAll() {
        return getSuccess(userService.findAll());
    }

}
