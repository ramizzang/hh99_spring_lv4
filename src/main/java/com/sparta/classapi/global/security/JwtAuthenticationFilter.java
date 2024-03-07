package com.sparta.classapi.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.classapi.domain.user.dto.LoginRequestDto;
import com.sparta.classapi.domain.user.entity.UserRoleEnum;
import com.sparta.classapi.global.jwt.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j(topic = "로그인 및 JWT 생성") //인증처리
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/api/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);

            return getAuthenticationManager().authenticate( // 인증시도
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getEmail(),
                            requestDto.getPassword(),
                            null
                    )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        String email = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        UserRoleEnum role = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getAuth();

        String token = jwtUtil.createToken(email, role);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);

        response.setStatus(HttpServletResponse.SC_OK); // 200 상태 코드 설정
        response.setContentType("application/json"); // 응답의 컨텐트 타입을 JSON으로 설정
        response.setCharacterEncoding("UTF-8"); // 문자 인코딩 설정

        // 성공 메시지를 JSON 형태로 작성
        String successMessage = "{\"success\": true, \"message\": \"로그인 성공\"}";

        try {
            response.getWriter().write(successMessage); // 응답에 성공 메시지 작성
        } catch (IOException e) {
            log.error("응답 메시지 작성 중 오류 발생", e);
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 상태 코드 설정
        response.setContentType("application/json"); // 응답의 컨텐트 타입을 JSON으로 설정
        response.setCharacterEncoding("UTF-8"); // 문자 인코딩 설정

        // 에러 메시지를 JSON 형태로 작성
        String errorMessage = "{\"error\": \"로그인 실패\", \"message\": \"" + failed.getMessage() + "\"}";

        try {
            response.getWriter().write(errorMessage); // 응답에 에러 메시지 작성
        } catch (IOException e) {
            log.error("응답 메시지 작성 중 오류 발생", e);
        }
    }

}