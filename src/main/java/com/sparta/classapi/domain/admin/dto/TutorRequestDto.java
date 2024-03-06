package com.sparta.classapi.domain.admin.dto;

import com.sparta.classapi.domain.admin.entity.Tutor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class TutorRequestDto {
    @NotBlank(message = "강사 이름을 입력해 주세요.")
    private String name;

    @NotNull(message = "강사의 경력을 입력해주세요")
    private Integer career;

    @NotBlank(message = "소속 회사를 입력해주세요")
    private String company;

    @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}$")
    @NotNull(message = "핸드폰 번호를 입력해주세요")
    private String phoneNumber;

    @NotNull(message = "강사 소개를 입력해 주세요")
    private String description;


    @Builder
    public TutorRequestDto(String name, Integer career, String company, String phoneNumber, String description) {
        this.name = name;
        this.career = career;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.description = description;
    }

    public Tutor toEntity() {
        return Tutor.builder()
                .name(name)
                .career(career)
                .company(company)
                .phoneNumber(phoneNumber)
                .description(description)
                .build();
    }
}

