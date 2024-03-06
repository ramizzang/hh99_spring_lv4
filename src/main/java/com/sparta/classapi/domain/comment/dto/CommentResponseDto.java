package com.sparta.classapi.domain.comment.dto;

import com.sparta.classapi.domain.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long id; // 댓글 id
    private String title; // 댓글 단 강의
    private String content; // 댓글 내용
    private String useremail; // 댓글 작성자

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.title = comment.getLecture().getTitle();
        this.content = comment.getContent();
        this.useremail = comment.getUser().getEmail();
    }
}
