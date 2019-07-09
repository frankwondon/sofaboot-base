package com.api.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.module.api.app","com.module.base.common","com.api.app"} )
@Slf4j
public class Application {
    public static void main(String[] args) {
        try {
            SpringApplication springApplication = new SpringApplication(Application.class);
            springApplication.run(args);
        } catch (Exception e) {
            log.error("Application start fail",e);
        }
    }
}
