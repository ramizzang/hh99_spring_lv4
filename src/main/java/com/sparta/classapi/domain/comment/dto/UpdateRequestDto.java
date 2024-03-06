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
public class UpdateRequestDto {
    private String content; // 댓글내용

    @Builder
    public UpdateRequestDto(String content) {
        this.content = content;
    }

}
