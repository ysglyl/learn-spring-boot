package com.bzdnet.learn.springboot.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {
        "com.bzdnet.learn.springboot.dao"
})
@EntityScan(basePackages = {
        "com.bzdnet.learn.springboot.entity"
})
public class JpaConfiguration {
}
