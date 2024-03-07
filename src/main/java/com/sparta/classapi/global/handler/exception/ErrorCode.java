package com.sparta.classapi.global.handler.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    //회원가입
    EMAIL_DUPLICATION("이미 가입된 이메일 입니다."),
    ADMIN_TOKEN_MISMATCH("관리자 토큰이 일치하지 않습니다."),

    //강사
    NOT_FOUND_TUTOR_ID("찾을 수 없는 강사 정보입니다."),

    //강의
    NOT_FOUND_LECTURE_ID("찾을 수 없는 강의 정보입니다."),

    //comment
    UNAUTHORIZED_USER("권한이 없는 사용자 입니다."),
    NOT_FOUND_COMMENT_ID("찾을 수 없는 댓글 정보입니다."),


    //공통
    INVALID_TYPE_VALUE("입력값을 확인하세요."),
    BAD_REQUEST_ADMIN_AUTHENTICATION("인증되지 않은 사용자 입니다."),
    ;

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}