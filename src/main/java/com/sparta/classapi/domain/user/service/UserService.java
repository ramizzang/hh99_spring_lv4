package com.sparta.classapi.domain.user.service;


import com.sparta.classapi.domain.user.dto.UserRequestDto.SignUpReq;
import com.sparta.classapi.domain.user.dto.UserResponseDto.SignUpResponseDto;
import com.sparta.classapi.domain.user.entity.User;
import com.sparta.classapi.domain.user.entity.UserRoleEnum;
import com.sparta.classapi.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화

    // ADMIN_TOKEN 일반사용자인지 관리자인지 구분하기 위한 토큰
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public SignUpResponseDto signUp(SignUpReq req) {

        //비밀번호 암호화
        String password = passwordEncoder.encode(req.getPassword());

        //이메일 중복 확인
        String email = req.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if(checkEmail.isPresent()){
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 사용자 권한 확인
        UserRoleEnum auth = UserRoleEnum.USER;
        if(req.isAdmin()){
            if(!ADMIN_TOKEN.equals(req.getAdminToken())){
                // 관리자 암호 불일치
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            auth = UserRoleEnum.ADMIN;
        }

        // 사용자 등록, 권한이랑 암호화한 비밀번호 보내서 저장
        User user = userRepository.save(req.toEntity(auth,password));
        return new SignUpResponseDto(user);
    }
}
