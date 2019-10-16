package com.bzdnet.learn.springboot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bzdnet.learn.springboot.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<UserEntity> {

    List<UserEntity> selectListBetweenAge(@Param("min") int min, @Param("max") int max);

}
