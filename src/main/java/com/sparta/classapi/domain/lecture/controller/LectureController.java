package com.sparta.classapi.domain.lecture.controller;


import com.sparta.classapi.domain.lecture.dto.GetLectureListDto;
import com.sparta.classapi.domain.lecture.dto.GetLectureResponseDto;
import com.sparta.classapi.domain.lecture.entity.LectureCategory;
import com.sparta.classapi.domain.lecture.service.LectureService;
import com.sparta.classapi.global.common.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LectureController {
    private final LectureService lectureService;


    @GetMapping("lecture/{lectureId}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseDto<GetLectureResponseDto> readLecture(@PathVariable Long lectureId) {

        return ResponseDto.success("선택한 강의 정보 입니다.", lectureService.getLecture(lectureId));
    }


    /*   카테고리별 강의 목록 조회 기능
       선택한 카테고리에 포함된 강의를 조회할 수 있습니다.
       모든 사용자가 강의를 조회할 수 있습니다.
       강사의 정보는 제외됩니다.
       조회된 강의 목록은 선택한 기준에 의해 정렬됩니다.
               강의명, 가격, 등록일 중 기준을 선택할 수 있습니다.
       내림차순, 오름차순을 선택할 수 있습니다.*/
    // readLectureList get /  /api/lecture?category=&select=
    @GetMapping("lecture")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseDto<List<GetLectureListDto>> readLectureList(@RequestParam(name = "category") LectureCategory category,
                                                                @RequestParam(name = "sortBy", required = false) String sortBy, // 내림차순, 오름차순
                                                                @RequestParam(name = "sortOrder", required = false) String select) { // 선택한 것


        return ResponseDto.success("조회가 완료됐습니다.",lectureService.getLecturesByCategory(category,sortBy,select));
    }


}
