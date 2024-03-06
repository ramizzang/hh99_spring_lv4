package com.sparta.classapi.domain.like.controller;

import com.sparta.classapi.domain.like.service.LikeService;
import com.sparta.classapi.domain.user.entity.User;
import com.sparta.classapi.global.common.ResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/like")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    @PostMapping("/lecture/{lectureId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto addLike(@PathVariable("lectureId") Long lectureId, HttpServletRequest req){ //헤더에서 유저정보 가져오기
        User user = (User) req.getAttribute("user");
        return ResponseDto.success("좋아요 클릭",likeService.addLike(lectureId,user));
    }

}
