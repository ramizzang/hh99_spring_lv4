package com.sparta.classapi.domain.user.service;


import com.sparta.classapi.domain.user.dto.LoginRequestDto;
import com.sparta.classapi.domain.user.dto.SignUpRequestDto;
import com.sparta.classapi.domain.user.dto.LoginResponseDto;
import com.sparta.classapi.domain.user.dto.SignUpResponseDto;
import com.sparta.classapi.domain.user.entity.User;
import com.sparta.classapi.domain.user.entity.UserRoleEnum;
import com.sparta.classapi.domain.user.repository.UserRepository;
import com.sparta.classapi.global.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화
    private final JwtUtil jwtUtil;

    // ADMIN_TOKEN 일반사용자인지 관리자인지 구분하기 위한 토큰
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public SignUpResponseDto signUp(SignUpRequestDto requestDto) {

        //비밀번호 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());

        //이메일 중복 확인
        String email = requestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if(checkEmail.isPresent()){
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 사용자 권한 확인
        UserRoleEnum auth = UserRoleEnum.USER;
        if(requestDto.isAdmin()){
            if(!ADMIN_TOKEN.equals(requestDto.getAdminToken())){
                // 관리자 암호 불일치
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            auth = UserRoleEnum.ADMIN;
        }

        // 사용자 등록, 권한이랑 암호화한 비밀번호 보내서 저장
        User user = userRepository.save(requestDto.toEntity(auth,password));
        return new SignUpResponseDto(user);
    }

//    public LoginResponseDto login(LoginRequestDto requestDto, HttpServletResponse res) {
//        String email = requestDto.getEmail();
//        String password = requestDto.getPassword();
//
//
//        // 존재하는 사용자인지 확인
//        User user = userRepository.findByEmail(email).orElseThrow(
//                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
//        );
//
//        // 존재하면 비밀번호 확인
//        if(!passwordEncoder.matches(password, user.getPassword())){
//            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
//        }
//
//        // 토큰 생성 및 저장, response 객체에 추가
//        String token = jwtUtil.createToken(user.getEmail(), user.getAuth());
//        jwtUtil.addJwtToCookie(token, res);
//
//        return new LoginResponseDto(user);
//    }
}
