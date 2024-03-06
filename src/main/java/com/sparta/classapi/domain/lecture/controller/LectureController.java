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


    @GetMapping("lecture")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseDto<List<GetLectureListDto>> readLectureList(@RequestParam(name = "category") LectureCategory category,
                                                                @RequestParam(name = "sortBy") String sortBy, // 내림차순, 오름차순
                                                                @RequestParam(name = "select") String select) { // 선택한 것

        return ResponseDto.success("조회가 완료됐습니다.",lectureService.getLecturesByCategory(category,sortBy,select));
    }


}
