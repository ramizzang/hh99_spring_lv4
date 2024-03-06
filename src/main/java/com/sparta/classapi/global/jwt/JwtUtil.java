package com.sparta.classapi.global.jwt;

import com.sparta.classapi.domain.user.entity.UserRoleEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {// jwt 관련된 기능을 모아둘거다!
    //util : 특정한 매개변수 혹은 파라미터에 대한 작업을 수행하는 메서드가 있는 클래스
    //다른 객체에 의존하지 않고 하나의 모듈로서 동작하는 클래스~ (문자열 조작, 날짜 포맷 조작 이런것들의 모음!)

    // JWT 데이터 생성시 필요한 데이터 넣기
    // Header KEY 값 (name:value) 이렇게쓸 때 그 key
    public static final String AUTHORIZATION_HEADER = "Authorization";
    // 사용자 권한 값의 KEY (사용자, 관리자 이런 권한!)
    public static final String AUTHORIZATION_KEY = "auth";
    // Token 식별자 (토큰의 prefix / 토큰의 앞에 붙는다 구분을 위해 끝에 공백!!)
    public static final String BEARER_PREFIX = "Bearer ";
    // 토큰 만료시간
    private final long TOKEN_TIME = 60 * 60 * 1000L; // 60분

    @Value("${jwt.secret.key}") // Base64 Encode 한 SecretKey,  application.properties에 선언한 값 사용하는 법
    private String secretKey;
    private Key key; // serect key를 담을 key 객체
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; // 암호화 할 알고리즘

    // 로그 설정, 롬복의 기능으로 @Sl4j 사용할 수도 있다
    public static final Logger logger = LoggerFactory.getLogger("JWT 관련 로그");

    @PostConstruct // 딱 한번만 받아오면 되는 값을 사용할 때 여러번 호출되는 것을 방지하기 위해서.
    public void init() {
        // secretkey 가 base64로 한번 인코딩 한 키이기 때문에 사용하기 위해 디코딩해준다
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    // JWT 생성(생성한 토큰은 쿠키를 생성해 헤더에 담을 수도 있고, 그냥 헤더에 토큰 자체를 반환 할 수도 있다)
    public String createToken(String email, UserRoleEnum role) {
        Date date = new Date();

        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(email) // 사용자 식별자값(ID)
                        .claim(AUTHORIZATION_KEY, role) // 사용자 권한 (key,value)
                        .setExpiration(new Date(date.getTime() + TOKEN_TIME)) // 만료 시간 date.getTime() : 현재시간
                        .setIssuedAt(date) // 발급일
                        .signWith(key, signatureAlgorithm) // 암호화 알고리즘 (secretkey, 알고리즘)
                        .compact();
    }

    // 생성된 토큰을 Cookie에 저장
    public void addJwtToCookie(String token, HttpServletResponse res) {
        try {
            // Cookie Value 에는 공백이 불가능해서 encoding 진행
            token = URLEncoder.encode(token, "utf-8").replaceAll("\\+", "%20");

            Cookie cookie = new Cookie(AUTHORIZATION_HEADER, token); // Name-Value
            cookie.setPath("/");

            // Response 객체에 Cookie 추가
            res.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
    }

    // Cookie에 들어있던 JWT 토큰을 substring
    public String substringToken(String tokenValue) { // 가져왔을 때 BEARER 이 있기 때문에 그것을 제거
        if (StringUtils.hasText(tokenValue) && tokenValue.startsWith(BEARER_PREFIX)) {
            //StringUtils.hasText(tokenValue) : 공백, 널 체크
            //tokenValue.startsWith(BEARER_PREFIX) : BEARER_PREFIX 로 시작하는지 체크
            return tokenValue.substring(7); // Bearer의 길이만큼 자르기
        }
        logger.error("Not Found Token");
        throw new NullPointerException("Not Found Token");
    }


    // JWT 토큰 검증
    public boolean validateToken(String token) {
        try {
            // 토큰의 위변조 확인
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException | SignatureException e) {
            logger.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token, 만료된 JWT token 입니다.");
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }
        return false;
    }

    // JWT에서 사용자 정보 가져오기
    public Claims getUserInfoFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    // HttpServletRequest 에서 Cookie Value : JWT 가져오기
    public String getTokenFromRequest(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies(); // 답겨있는 쿠키들 배열로 가져오기
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(AUTHORIZATION_HEADER)) { // 쿠키 이름이 우리가 가져오려는 쿠키면
                    try {
                        // Encode 되어 넘어간 Value 다시 Decode
                        return URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        return null;
                    }
                }
            }
        }
        return null;
    }

}