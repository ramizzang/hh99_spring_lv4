package com.sparta.classapi.global.common;

import com.sparta.classapi.domain.lecture.entity.Lecture;
import com.sparta.classapi.domain.lecture.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommonService {
    private final LectureRepository lectureRepository;

    public Lecture findLectureById(Long lectureId) { //lecture 강의 찾기
        return lectureRepository.findById(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의 입니다."));
    }

}
