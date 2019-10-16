package com.bzdnet.learn.springboot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bzdnet.learn.springboot.dao.UserMapper;
import com.bzdnet.learn.springboot.dao.UserRepository;
import com.bzdnet.learn.springboot.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper, UserEntity> {

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> selectListBetweenAge(int min, int max) {
        return baseMapper.selectListBetweenAge(min, max);
    }

    public Iterator<UserEntity> findAll() {
        return userRepository.findAll().iterator();
    }

    public void findPaged() {
        Iterator<UserEntity> all = userRepository.findAll().iterator();
        Page<UserEntity> page = userRepository.findAll(PageRequest.of(1, 10));
        userRepository.save(new UserEntity());
        userRepository.saveAll(new ArrayList<>());
        userRepository.deleteById(1L);
    }

}
