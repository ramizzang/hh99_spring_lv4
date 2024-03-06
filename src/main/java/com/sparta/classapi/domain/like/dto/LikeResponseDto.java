package com.sparta.classapi.domain.like.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class LikeResponseDto {
    private Long lectureId;
    private Integer likes;

    public LikeResponseDto(Long lectureId, Integer likes) {
        this.lectureId = lectureId;
        this.likes = likes;
    }
}
