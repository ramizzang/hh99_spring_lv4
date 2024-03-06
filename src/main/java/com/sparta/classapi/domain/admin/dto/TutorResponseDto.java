package com.sparta.classapi.domain.admin.dto;

import com.sparta.classapi.domain.admin.entity.Tutor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class TutorResponseDto {
    private Long id;
    private String name;
    private Integer career;
    private String company;
    private String phoneNumber;
    private String description; // 강사 이름


    public TutorResponseDto(Tutor tutor) {
        this.id = tutor.getId();
        this.name = tutor.getName();
        this.career = tutor.getCareer();
        this.company = tutor.getCompany();
        this.phoneNumber = tutor.getPhoneNumber();
        this.description = tutor.getDescription();
    }
}

