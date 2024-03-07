package com.sparta.classapi.domain.comment.controller;

import com.sparta.classapi.domain.comment.dto.CommentRequestDto;
import com.sparta.classapi.domain.comment.dto.CommentResponseDto;
import com.sparta.classapi.domain.comment.dto.UpdateRequestDto;
import com.sparta.classapi.domain.comment.service.CommentService;
import com.sparta.classapi.global.common.ResponseDto;
import com.sparta.classapi.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    //댓글 leaveComment post comment
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<CommentResponseDto> leaveComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails ) {
        return ResponseDto.success("댓글이 등록되었습니다.", commentService.leaveComment(requestDto, userDetails.getUser()));
    }

    //    댓글수정 updateComment put comment/{commentId}
    @PutMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<CommentResponseDto> updateComment(@PathVariable Long commentId,
                                                         @RequestBody UpdateRequestDto requestDto,
                                                         @AuthenticationPrincipal UserDetailsImpl userDetails) throws IllegalAccessException {
        return ResponseDto.success("댓글이 수정되었습니다.", commentService.updateComment(commentId, requestDto, userDetails.getUser()));
    }

//    댓글삭제 deleteComment delete comment/{commentId}
    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<Long> deleteComment(@PathVariable Long commentId,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails) throws IllegalAccessException {
        return ResponseDto.success("댓글이 삭제되었습니다.", commentService.deleteComment(commentId, userDetails.getUser()));
    }
}
