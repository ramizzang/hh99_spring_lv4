package com.sparta.classapi.domain.like.service;

import com.sparta.classapi.domain.lecture.entity.Lecture;
import com.sparta.classapi.domain.lecture.repository.LectureRepository;
import com.sparta.classapi.domain.like.dto.LikeResponseDto;
import com.sparta.classapi.domain.like.entity.Like;
import com.sparta.classapi.domain.like.repository.LikeRepository;
import com.sparta.classapi.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LectureRepository lectureRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public LikeResponseDto addLike(Long lectureId, User user) {
        // 보드정보, 유저정보 받아옴
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 강의 입니다.")
        );
        // 중복 좋아요 확인
        if(!likeRepository.existsByLectureAndUser(lecture, user)){
            Like like = Like.builder()
                    .lecture(lecture)
                    .user(user)
                    .build();
            likeRepository.save(like);
            // 강의 좋아요 수 증가
            lecture.incrementLikes();
            return new LikeResponseDto(lectureId, lecture.getLikes());
        }else{
            // 중복인 경우 좋아요 정보 삭제
            likeRepository.deleteByLectureAndUser(lecture, user);
            // 좋아요 수 감소
            lecture.decrementLikes();
            return new LikeResponseDto(lectureId, lecture.getLikes());
        }
    }
}
