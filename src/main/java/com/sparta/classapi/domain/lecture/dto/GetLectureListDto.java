package com.sparta.classapi.domain.lecture.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class GetLectureListDto {
    private Long id;
    private String title;
    private Integer like;
    private Integer price;
    private String description;
    private LectureCategory category;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;


    public GetLectureListDto(Lecture lecture) { // response dto 하나로 처리하려고 tutor 정보 포함하는거 boolean 처리
        this.id = lecture.getId();
        this.title = lecture.getTitle();
        this.like = lecture.getLikes();
        this.price = lecture.getPrice();
        this.description = lecture.getDescription();
        this.category = lecture.getCategory();
        this.createdAt = lecture.getCreatedAt();

    }
}


