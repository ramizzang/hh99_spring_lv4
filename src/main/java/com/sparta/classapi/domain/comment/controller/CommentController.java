package com.sparta.classapi.domain.comment.controller;

import com.sparta.classapi.domain.comment.dto.CommentRequestDto;
import com.sparta.classapi.domain.comment.dto.CommentResponseDto;
import com.sparta.classapi.domain.comment.service.CommentService;
import com.sparta.classapi.domain.user.entity.User;
import com.sparta.classapi.global.common.ResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    //댓글 leaveComment post comment
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<CommentResponseDto> leaveComment(@RequestBody CommentRequestDto requestDto, HttpServletRequest req) {
        User user = (User) req.getAttribute("user");
        return ResponseDto.success("댓글이 등록되었습니다.",commentService.leaveComment(requestDto,user));
    }

    //댓글수정 updateComment put comment/{commentId}
//    @PutMapping("/{commentId}")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseDto<CommentResponseDto> updateComment(@PathVariable Long CommentId, HttpServletRequest req) {
//        User user = (User) req.getAttribute("user");
//        return ResponseDto.success("댓글이 수정되었습니다.",commentService.updateComment(CommentId,user));
//    }


    //댓글삭제 deleteComment delete comment/{commentId}
}
