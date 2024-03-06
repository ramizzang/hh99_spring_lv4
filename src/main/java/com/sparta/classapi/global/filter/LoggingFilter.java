package com.sparta.classapi.global.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j(topic = "LoggingFilter") // 로깅 찍을 때 topic 의 이름으로 등록이 된다.
@Component
@Order(1) //filter의 순서를 지정해준다.(인증,인가보다 먼저 수행하게 하려고 1)
public class LoggingFilter implements Filter { // filter의 역할을 수행하기 위해서 filter interface implements
    @Override // filtercain : filter를 이동할 때 사용하기 위해 받는다
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 전처리 어떤 요청인지 로그찍기 -> 다음 필터로 이동 -> 작업끝나며 다시 로그 찍힘
        HttpServletRequest httpServletRequest = (HttpServletRequest) request; // HttpServletRequest 로 캐스팅 한번 한다
        String url = httpServletRequest.getRequestURI(); // url 정보 가져오기
        log.info(url); // log 찍기

        // 다음 Filter 로 이동
        chain.doFilter(request, response); 

        // 후처리
        log.info("비즈니스 로직 완료");
    }
}