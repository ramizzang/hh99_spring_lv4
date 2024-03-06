package com.sparta.classapi.domain.user.dto;

import com.sparta.classapi.domain.user.entity.Gender;
import com.sparta.classapi.domain.user.entity.User;
import com.sparta.classapi.domain.user.entity.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    @Getter
    @NoArgsConstructor
    public static class SignUpResponseDto{
        private Long id;
        private String email;
        private Gender gender;
        private String phoneNumber;
        private String address;
        private UserRoleEnum auth;


        public SignUpResponseDto(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.gender = user.getGender();
            this.phoneNumber = user.getPhoneNumber();
            this.address = user.getAddress();
            this.auth = user.getAuth();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class LoginResponseDto{
        private Long id;
        private String email;

        public LoginResponseDto(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
        }
    }

}
