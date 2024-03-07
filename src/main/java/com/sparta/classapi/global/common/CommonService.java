package com.sparta.classapi.global.common;

import com.sparta.classapi.domain.lecture.entity.Lecture;
import com.sparta.classapi.domain.lecture.repository.LectureRepository;
import com.sparta.classapi.global.handler.exception.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.sparta.classapi.global.handler.exception.ErrorCode.NOT_FOUND_LECTURE_ID;

@Service
@RequiredArgsConstructor
public class CommonService {
    private final LectureRepository lectureRepository;

    public Lecture findLectureById(Long lectureId) { //lecture 강의 찾기
        return lectureRepository.findById(lectureId)
                .orElseThrow(() ->new CustomApiException(NOT_FOUND_LECTURE_ID.getMessage()));
    }

}
