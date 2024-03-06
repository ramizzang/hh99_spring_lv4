package com.sparta.classapi.domain.user.dto;

import com.sparta.classapi.domain.user.entity.Gender;
import com.sparta.classapi.domain.user.entity.User;
import com.sparta.classapi.domain.user.entity.UserRoleEnum;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class UserRequestDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PACKAGE)
    public static class SignUpReq{
        @Email(message = "이메일을 입력해주세요.")
        @NotBlank(message = "이메일을 입력해주세요.")
        private String email;

        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$")
        private String password;

        @NotNull(message = "성별을 선택하세요")
        private Gender gender; // 성별 저장

        @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}$")
        @NotBlank(message = "전화번호를 입력해주세요.")
        private String phoneNumber;

        @Column(name = "address", nullable = false)
        @NotBlank(message = "주소를 입력해주세요.")
        private String address;

        private boolean admin = false; // 관리자 확인

        private String adminToken = ""; // 관리자일 때 토큰

        @Builder
        public SignUpReq(String email, String password, Gender gender, String phoneNumber, String address, boolean admin) {
            this.email = email;
            this.password = password;
            this.gender = gender;
            this.phoneNumber = phoneNumber;
            this.address = address;
            this.admin = admin;
        }

        public User toEntity(UserRoleEnum auth,String password){
            return User.builder()
                    .email(email)
                    .password(password)
                    .gender(gender)
                    .phoneNumber(phoneNumber)
                    .address(address)
                    .auth(auth)
                    .build();
        }
    }

}
