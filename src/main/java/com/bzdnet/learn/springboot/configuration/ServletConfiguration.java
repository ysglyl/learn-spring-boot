package com.bzdnet.learn.springboot.configuration;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ServletComponentScan("com.bzdnet.learn.springboot.servlet")
public class ServletConfiguration {
}
