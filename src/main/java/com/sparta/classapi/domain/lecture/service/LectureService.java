package com.sparta.classapi.domain.lecture.service;

import com.sparta.classapi.domain.admin.dto.LectureResponseDto;
import com.sparta.classapi.domain.lecture.dto.GetLectureResponseDto;
import com.sparta.classapi.domain.lecture.entity.Lecture;
import com.sparta.classapi.domain.lecture.entity.LectureCategory;
import com.sparta.classapi.domain.lecture.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureRepository lectureRepository;

    public GetLectureResponseDto getLecture(Long lectureId) {
        // 선택한 강의 가져오기, 존재하는 강의인지 확인
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(
                () -> new IllegalArgumentException("선택한 강의가 없습니다"));

        // 존재하는 강의 반환
        return new GetLectureResponseDto(lecture,true);
    }

    public List<GetLectureResponseDto> getLecturesByCategory(LectureCategory category, String sortBy, String select) {
        // 카테고리에별 강의 가져오기
        List<Lecture> lectureList = lectureRepository.findAllByCategory(category); //전체 리스트 가져오기, 아무것도 선택안했을때 기본
        if(sortBy == null && select == null){//얘네값이 없으면
            //전체 리스트 반환
            return lectureList.stream().map(lecture -> new GetLectureResponseDto(lecture, false)).collect(Collectors.toList());
        }else{
            // 값이 있으면 정렬
            if(sortBy.equals("desc")){ // 내림차순
                if(select.equals("title")){
                    lectureList = lectureRepository.findAllByCategoryOrderByTitleDesc(category);
                } else if (select.equals("price")) {
                    lectureList = lectureRepository.findAllByCategoryOrderByPriceDesc(category);
                }else if(select.equals("createdAt")){
                    lectureList = lectureRepository.findAllByCategoryOrderByCreatedAtDesc(category);
                }
            }else{ // 오름차순
                if(select.equals("title")){
                    lectureList = lectureRepository.findAllByCategoryOrderByTitleAsc(category);
                } else if (select.equals("price")) {
                    lectureList = lectureRepository.findAllByCategoryOrderByPriceAsc(category);
                }else if(select.equals("createdAt")){
                    lectureList = lectureRepository.findAllByCategoryOrderByCreatedAtAsc(category);
                }
            } //else 문 끝
        }


        return lectureList.stream().map(lecture -> new GetLectureResponseDto(lecture, false)).collect(Collectors.toList());

    }
}
