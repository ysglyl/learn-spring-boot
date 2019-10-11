package com.bzdnet.learn.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;

@SpringBootApplication
public class Application {

    public static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.addListeners((applicationEvent) -> {
                    if (applicationEvent instanceof ApplicationReadyEvent) {
                        log.info("Application had already run!!!");
                    }
                }
        );
        app.run(args);
    }

}
