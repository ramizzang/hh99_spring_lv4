package com.sparta.classapi.domain.admin.controller;

import com.sparta.classapi.domain.admin.dto.LectureRequestDto;
import com.sparta.classapi.domain.admin.dto.LectureResponseDto;
import com.sparta.classapi.domain.admin.dto.TutorRequestDto;
import com.sparta.classapi.domain.admin.dto.TutorResponseDto;
import com.sparta.classapi.domain.admin.service.AdminService;
import com.sparta.classapi.domain.user.entity.UserRoleEnum;
import com.sparta.classapi.global.common.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    //강사 등록, 강의 등록 처리
    @PostMapping("/tutor")
    @Secured(UserRoleEnum.Authority.ADMIN)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseDto<TutorResponseDto> registerLecture(@RequestBody TutorRequestDto requestDto){

        return ResponseDto.success("강의 등록에 성공하였습니다.",adminService.registerTutor(requestDto));
    }

    @PostMapping("/lecture")
    @Secured(UserRoleEnum.Authority.ADMIN)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseDto<LectureResponseDto> registerLecture(@RequestBody LectureRequestDto requestDto){

        return ResponseDto.success("강의 등록에 성공하였습니다.",adminService.registerLecture(requestDto));
    }
}
