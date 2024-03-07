package com.sparta.classapi.domain.user.controller;

import com.sparta.classapi.domain.user.dto.SignUpRequestDto;
import com.sparta.classapi.domain.user.dto.LoginRequestDto;
import com.sparta.classapi.domain.user.dto.SignUpResponseDto;
import com.sparta.classapi.domain.user.dto.LoginResponseDto;
import com.sparta.classapi.domain.user.service.UserService;
import com.sparta.classapi.global.common.ResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseDto<SignUpResponseDto> signUp(@Valid @RequestBody SignUpRequestDto requestDto){
        return ResponseDto.success("회원가입이 완료되었습니다." ,userService.signUp(requestDto));
    }

//    @PostMapping("/login")
//    @ResponseStatus(value = HttpStatus.OK)
//    public ResponseDto<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto requestDto, HttpServletResponse res){
//        return ResponseDto.success("로그인 성공" ,userService.login(requestDto,res));
//    }

}
