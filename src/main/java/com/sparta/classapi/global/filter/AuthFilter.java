package com.sparta.classapi.global.filter;


import com.sparta.classapi.domain.user.entity.User;
import com.sparta.classapi.domain.user.entity.UserRoleEnum;
import com.sparta.classapi.domain.user.repository.UserRepository;
import com.sparta.classapi.global.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Slf4j(topic = "AuthFilter")
@Component
@Order(2)
public class AuthFilter extends OncePerRequestFilter { // 인증 및 인가처리를 하는 필터

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthFilter(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();

        // 그렇다면 인증 하지 않고 바로 다음 filter로 이동한다
        if (StringUtils.hasText(url) && (url.startsWith("/api/signup") || url.startsWith("/api/login") || url.startsWith("/api/lecture"))) {
            log.info("인증 처리를 하지 않는 url : " + url);
            // 회원가입, 로그인, 강의 조회 관련 API 는 인증 필요없이 요청 진행
            filterChain.doFilter(request, response); // 다음 Filter 로 이동
        } else {
            // 나머지 API 요청은 인증 처리 진행
            // 토큰 확인
            String tokenValue = jwtUtil.getTokenFromRequest(httpServletRequest);

            if (StringUtils.hasText(tokenValue)) { // 토큰이 존재하면 검증 시작
                // JWT 토큰 substring
                String token = jwtUtil.substringToken(tokenValue);

                // 토큰 검증
                if (!jwtUtil.validateToken(token)) {
                    throw new IllegalArgumentException("Token Error");
                }

                // 토큰에서 사용자 정보 가져오기
                Claims info = jwtUtil.getUserInfoFromToken(token);

                User user = userRepository.findByEmail(info.getSubject()).orElseThrow(() -> new NullPointerException("Not Found User"));

                // 권한 설정(강사 등록, 강의등록)
                if (StringUtils.hasText(url) && (url.equals("/lectures") || url.equals("/tutors"))) {
                    // JWT 토큰에서 사용자의 ROLE을 가져옴
                    String auth = (String) info.get(JwtUtil.AUTHORIZATION_KEY);

                    // 사용자의 권한을 ADMIN과 비교
                    if (!Objects.equals(UserRoleEnum.valueOf(auth).getAuthority(), UserRoleEnum.ADMIN.getAuthority())) {
                        throw new IllegalArgumentException("ADMIN만 접근 가능한 기능입니다.");
                    }
                }

                request.setAttribute("user", user);
                filterChain.doFilter(request, response); // 다음 Filter 로 이동
            } else {
                throw new IllegalArgumentException("Not Found Token");
            }
        }
    }
}