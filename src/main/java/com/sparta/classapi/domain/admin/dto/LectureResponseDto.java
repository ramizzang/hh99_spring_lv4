package com.sparta.classapi.domain.admin.dto;

import com.sparta.classapi.domain.lecture.entity.Lecture;
import com.sparta.classapi.domain.lecture.entity.LectureCategory;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LectureResponseDto {
    private Long id;
    private String title;
    private Integer price;
    private String description;
    private LectureCategory category;
    private LocalDateTime createdAt;
    private String tutorName; // 강사 이름


    public LectureResponseDto(Lecture lecture) {
        this.id = lecture.getId();
        this.title = lecture.getTitle();
        this.price = lecture.getPrice();
        this.description = lecture.getDescription();
        this.category = lecture.getCategory();
        this.createdAt = lecture.getCreatedAt();
        this.tutorName = lecture.getTutor().getName();
    }
}

