package com.bzdnet.learn.springboot.dao;

import com.bzdnet.learn.springboot.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.RepositoryDefinition;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

}
