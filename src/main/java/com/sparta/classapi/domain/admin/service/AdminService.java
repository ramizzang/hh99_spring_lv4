package com.sparta.classapi.domain.admin.service;


import com.sparta.classapi.domain.admin.dto.LectureRequestDto;
import com.sparta.classapi.domain.admin.dto.LectureResponseDto;
import com.sparta.classapi.domain.admin.dto.TutorRequestDto;
import com.sparta.classapi.domain.admin.dto.TutorResponseDto;
import com.sparta.classapi.domain.admin.entity.Tutor;
import com.sparta.classapi.domain.admin.repository.TutorRepository;
import com.sparta.classapi.domain.lecture.entity.Lecture;
import com.sparta.classapi.domain.lecture.repository.LectureRepository;
import com.sparta.classapi.global.handler.exception.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.sparta.classapi.global.handler.exception.ErrorCode.NOT_FOUND_TUTOR_ID;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final LectureRepository lectureRepository;
    private final TutorRepository tutorRepository;

    public TutorResponseDto registerTutor(TutorRequestDto requestDto) {
        // 강사 등록
        return new TutorResponseDto(tutorRepository.save(requestDto.toEntity()));
    }

    public LectureResponseDto registerLecture(LectureRequestDto requestDto) {
        Tutor tutor = tutorRepository.findById(requestDto.getTutorId()).orElseThrow( //강사 정보 없으면 실패
                () -> new CustomApiException(NOT_FOUND_TUTOR_ID.getMessage()));
        return new LectureResponseDto(lectureRepository.save(requestDto.toEntity(tutor)));
    }
}