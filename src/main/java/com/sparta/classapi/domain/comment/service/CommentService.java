package com.sparta.classapi.domain.comment.service;

import com.sparta.classapi.domain.comment.dto.CommentRequestDto;
import com.sparta.classapi.domain.comment.dto.CommentResponseDto;
import com.sparta.classapi.domain.comment.dto.UpdateRequestDto;
import com.sparta.classapi.domain.comment.entity.Comment;
import com.sparta.classapi.domain.comment.repository.CommentRepository;
import com.sparta.classapi.domain.lecture.entity.Lecture;
import com.sparta.classapi.domain.user.entity.User;
import com.sparta.classapi.global.common.CommonService;
import com.sparta.classapi.global.handler.exception.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sparta.classapi.global.handler.exception.ErrorCode.NOT_FOUND_COMMENT_ID;
import static com.sparta.classapi.global.handler.exception.ErrorCode.UNAUTHORIZED_USER;

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

    @Transactional
    public CommentResponseDto updateComment(Long commentId, UpdateRequestDto requestDto, User user) throws IllegalAccessException {
        // 존재하는 댓글인지 확인, 댓글에 있는 강의 정보 가져온다
        Comment comment = findCommentById(commentId);
        // 본인 확인
        checkUserAuthorization(comment.getUser(), user);
        // 댓글 수정 진행
        comment.updateContent(requestDto.getContent());

        return new CommentResponseDto(comment);
    }

    @Transactional
    public Long  deleteComment(Long commentId, User user) {
        // 존재하는 댓글인지 확인, 댓글에 있는 강의 정보 가져온다
        Comment comment = findCommentById(commentId);
        // 본인 확인
        checkUserAuthorization(comment.getUser(), user);
        // 댓글 삭제 진행
        commentRepository.delete(comment);
        return commentId; // 삭제된 댓글의 ID 반환
    }

    private Comment findCommentById(Long commentId){
        return commentRepository.findById(commentId).orElseThrow(
                () -> new CustomApiException(NOT_FOUND_COMMENT_ID.getMessage())
        );
    }

    private void checkUserAuthorization(User commentUser, User requestUser) {
        if (!commentUser.getId().equals(requestUser.getId())) {
            throw new CustomApiException(UNAUTHORIZED_USER.getMessage());
        }
    }

}
