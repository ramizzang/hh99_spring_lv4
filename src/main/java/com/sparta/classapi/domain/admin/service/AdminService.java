package com.sparta.classapi.domain.admin.service;


import com.sparta.classapi.domain.admin.dto.LectureRequestDto;
import com.sparta.classapi.domain.admin.dto.LectureResponseDto;
import com.sparta.classapi.domain.admin.dto.TutorRequestDto;
import com.sparta.classapi.domain.admin.dto.TutorResponseDto;
import com.sparta.classapi.domain.admin.entity.Tutor;
import com.sparta.classapi.domain.admin.repository.TutorRepository;
import com.sparta.classapi.domain.lecture.entity.Lecture;
import com.sparta.classapi.domain.lecture.repository.LectureRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final LectureRepository lectureRepository;
    private final TutorRepository tutorRepository;

    public TutorResponseDto registerTutor(TutorRequestDto req) {
        // 강사 등록
        Tutor tutor = tutorRepository.save(req.toEntity());
        return new TutorResponseDto(tutor);
    }

    public LectureResponseDto registerLecture(LectureRequestDto req) {
        Tutor tutor = tutorRepository.findById(req.getTutorId()).orElseThrow( //강사 정보 없으면 실패
                () -> new EntityNotFoundException("강사 정보가 없습니다."));
        Lecture lecture = lectureRepository.save(req.toEntity(tutor));
        return new LectureResponseDto(lecture);
    }
}