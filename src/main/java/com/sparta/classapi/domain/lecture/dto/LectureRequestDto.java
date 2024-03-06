package com.sparta.classapi.domain.lecture.dto;

import com.sparta.classapi.domain.lecture.entity.Lecture;
import com.sparta.classapi.domain.lecture.entity.LectureCategory;
import com.sparta.classapi.domain.admin.entity.Tutor;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class LectureRequestDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PACKAGE)
    public static class RegisterLectureRequestDto{
        @NotBlank(message = "강의 제목을 입력해 주세요.")
        private String title;
        @NotNull(message = "강의 가격을 입력해 주세요.")
        @Min(100)
        private Integer price;

        @NotBlank(message = "강의 설명을 입력해 주세요.")
        private String description;

        @NotNull(message = "강의 카테고리를 입력해주세요.")
        private LectureCategory category;

        @NotNull(message = "강사 ID를 입력해 주세요.")
        private Long tutorId;


        @Builder
        public RegisterLectureRequestDto(String title, Integer price, String description, LectureCategory category, Long tutorId) {
            this.title = title;
            this.price = price;
            this.description = description;
            this.category = category;
            this.tutorId = tutorId;
        }

        public Lecture toEntity(Tutor tutor){
            return Lecture.builder()
                    .title(title)
                    .price(price)
                    .description(description)
                    .category(category)
                    .tutor(tutor)
                    .build();
        }
    }
}
