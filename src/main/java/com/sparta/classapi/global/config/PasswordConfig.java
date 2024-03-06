package com.sparta.classapi.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // Bean으로 등록할 메서드가 있는 클래스에 추가해주기
public class PasswordConfig {

    @Bean // Bean으로 등록할 메서드에 추가
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}