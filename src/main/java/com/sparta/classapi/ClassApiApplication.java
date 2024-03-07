package com.sparta.classapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// security 인증 기능 제외
@SpringBootApplication
public class ClassApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassApiApplication.class, args);
    }

}
