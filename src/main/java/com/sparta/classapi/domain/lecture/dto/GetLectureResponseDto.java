package com.sparta.classapi.domain.lecture.dto;

import com.sparta.classapi.domain.admin.entity.Tutor;
import com.sparta.classapi.domain.lecture.entity.Lecture;
import com.sparta.classapi.domain.lecture.entity.LectureCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class GetLectureResponseDto {
    private Long id;
    private String title;
    private Integer price;
    private String description;
    private LectureCategory category;
    private LocalDateTime createdAt;
    private List<TutorInfo> tutors;


//    public GetLectureResponseDto(Lecture lecture) { // response dto 하나로 처리하려고 tutor 정보 포함하는거 boolean 처리
//        this.id = lecture.getId();
//        this.title = lecture.getTitle();
//        this.price = lecture.getPrice();
//        this.description = lecture.getDescription();
//        this.category = lecture.getCategory();
//        this.createdAt = lecture.getCreatedAt();
//
//        // 튜터 정보
//        this.tutors = List.of(new TutorInfo(lecture.getTutor()));
//    }

    public GetLectureResponseDto(Lecture lecture, boolean includeTutor) {
        this.id = lecture.getId();
        this.title = lecture.getTitle();
        this.price = lecture.getPrice();
        this.description = lecture.getDescription();
        this.category = lecture.getCategory();
        this.createdAt = lecture.getCreatedAt();

        if (includeTutor) {
            // 튜터 정보
            this.tutors = List.of(new TutorInfo(lecture.getTutor()));
        }
    }

    @Getter
    @NoArgsConstructor
    private static class TutorInfo { // 튜터 정보 담아주기
        private String tutorName;
        private Integer career;
        private String company;
        private String tutorDescription;

        public TutorInfo(Tutor tutor) {
            this.tutorName = tutor.getName();
            this.career = tutor.getCareer();
            this.company = tutor.getCompany();
            this.tutorDescription = tutor.getDescription();
        }
    }
}


