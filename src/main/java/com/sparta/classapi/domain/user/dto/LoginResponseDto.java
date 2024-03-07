package com.sparta.classapi.domain.user.dto;

import com.sparta.classapi.domain.user.entity.User;
import lombok.Getter;

@Getter
public class LoginResponseDto{
    private Long id;
    private String email;

    public LoginResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
    }
}
