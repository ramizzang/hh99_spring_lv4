package com.sparta.classapi.domain.user.controller;

import com.sparta.classapi.domain.user.dto.UserRequestDto.SignUpReq;
import com.sparta.classapi.domain.user.dto.UserResponseDto.SignUpResponseDto;
import com.sparta.classapi.domain.user.service.UserService;
import com.sparta.classapi.global.common.ResponseDto;
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
    public ResponseDto<SignUpResponseDto> signUp(@Valid @RequestBody SignUpReq req){

//        userService.signUp(req);
        return ResponseDto.success("회원가입이 완료되었습니다." ,userService.signUp(req));
    }

}
