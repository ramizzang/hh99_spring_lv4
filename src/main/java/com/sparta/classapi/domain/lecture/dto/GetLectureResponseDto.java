package com.sparta.classapi.domain.lecture.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sparta.classapi.domain.admin.entity.Tutor;
import com.sparta.classapi.domain.comment.entity.Comment;
import com.sparta.classapi.domain.lecture.entity.Lecture;
import com.sparta.classapi.domain.lecture.entity.LectureCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class GetLectureResponseDto {
    private Long id;
    private String title;
    private Integer like;
    private Integer price;
    private String description;
    private LectureCategory category;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    private List<TutorInfo> tutors;
    private List<CommentInfo> commentList;




    public GetLectureResponseDto(Lecture lecture) { // response dto 하나로 처리하려고 tutor 정보 포함하는거 boolean 처리
        this.id = lecture.getId();
        this.title = lecture.getTitle();
        this.like = lecture.getLikes();
        this.price = lecture.getPrice();
        this.description = lecture.getDescription();
        this.category = lecture.getCategory();
        this.createdAt = lecture.getCreatedAt();

        // 튜터 정보
        this.tutors = List.of(new TutorInfo(lecture.getTutor()));

        // 댓글 정보
        this.commentList = lecture.getCommentList().stream()
                .map(comment -> new CommentInfo(comment.getUser().getEmail(), comment.getContent()))
                .collect(Collectors.toList());
    }

    @Getter
    @NoArgsConstructor
    private static class CommentInfo {
        private String authorEmail;
        private String content;

        public CommentInfo(String authorEmail, String content) {
            this.authorEmail = authorEmail; //댓글 작성자
            this.content = content; // 댓글 내용
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



