package com.sparta.classapi.domain.comment.service;

import com.sparta.classapi.domain.comment.dto.CommentRequestDto;
import com.sparta.classapi.domain.comment.dto.CommentResponseDto;
import com.sparta.classapi.domain.comment.entity.Comment;
import com.sparta.classapi.domain.comment.repository.CommentRepository;
import com.sparta.classapi.domain.lecture.entity.Lecture;
import com.sparta.classapi.domain.user.entity.User;
import com.sparta.classapi.global.common.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommonService commonService;

    public CommentResponseDto leaveComment(CommentRequestDto requestDto, User user) {
        // 강의 확인
        Lecture lecture = commonService.findLectureById(requestDto.getLecturedId());

        // 존재하면 댓글 등록
        Comment comment =  commentRepository.save(requestDto.toEntity(lecture,user));

        return new CommentResponseDto(comment);
    }

//    @Transactional
//    public CommentResponseDto updateComment(Long commentId, User user) {
//
//        return new CommentResponseDto(comment);
//    }
}
