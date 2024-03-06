package com.sparta.classapi.domain.comment.dto;

import com.sparta.classapi.domain.comment.entity.Comment;
import com.sparta.classapi.domain.lecture.entity.Lecture;
import com.sparta.classapi.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class CommentRequestDto {
    private String content; // 댓글내용
    private Long lecturedId; // 강의 ID

    @Builder
    public CommentRequestDto(String content, Long lecturedId) {
        this.content = content;
        this.lecturedId = lecturedId;
    }

    public Comment toEntity(Lecture lecture, User user){
        return Comment.builder()
                .content(content)
                .lecture(lecture)
                .user(user)
                .build();
    }
}
