package com.sparta.classapi.domain.lecture.service;

import com.sparta.classapi.domain.lecture.dto.GetLectureListDto;
import com.sparta.classapi.domain.lecture.dto.GetLectureResponseDto;
import com.sparta.classapi.domain.lecture.entity.Lecture;
import com.sparta.classapi.domain.lecture.entity.LectureCategory;
import com.sparta.classapi.domain.lecture.repository.LectureRepository;
import com.sparta.classapi.global.common.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;
    private final CommonService commonService;

    public GetLectureResponseDto getLecture(Long lectureId) {
        // 선택한 강의 가져오기, 존재하는 강의인지 확인
        Lecture lecture = commonService.findLectureById(lectureId);

        // 존재하는 강의 반환
        return new GetLectureResponseDto(lecture);
    }

    public List<GetLectureListDto> getLecturesByCategory(LectureCategory category, String sortBy, String select) {
        // 카테고리에별 강의 가져오기
        List<Lecture> lectureList = new ArrayList<>();
        // 값이 있으면 정렬
        if (sortBy.equals("desc")) { // 내림차순
            if (select.equals("title")) {
                lectureList = lectureRepository.findAllByCategoryOrderByTitleDesc(category);
            } else if (select.equals("price")) {
                lectureList = lectureRepository.findAllByCategoryOrderByPriceDesc(category);
            } else if (select.equals("createdAt")) {
                lectureList = lectureRepository.findAllByCategoryOrderByCreatedAtDesc(category);
            }
        } else if (sortBy.equals("asc")) { // 오름차순
            if (select.equals("title")) {
                lectureList = lectureRepository.findAllByCategoryOrderByTitleAsc(category);
            } else if (select.equals("price")) {
                lectureList = lectureRepository.findAllByCategoryOrderByPriceAsc(category);
            } else if (select.equals("createdAt")) {
                lectureList = lectureRepository.findAllByCategoryOrderByCreatedAtAsc(category);
            }
        } //if-else 문 끝

        return lectureList.stream().map(lecture -> new GetLectureListDto(lecture)).collect(Collectors.toList());

    }
}
