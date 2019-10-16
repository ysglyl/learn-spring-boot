package com.bzdnet.learn.springboot.configuration;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    @Bean
    public Docket swagger2DocketBean(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder().title("Spring fox Swagger2").description("学习Springboot的过程笔记").contact(new Contact("Yushigui", "https://github.com/ysglyl", "yushigui1990@foxmail.com")).build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bzdnet.learn.springboot.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
