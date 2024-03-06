package com.sparta.classapi.domain.lecture.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LectureCategory {

    SPRING("Spring"), REACT("React"),NODE("Node");
    private final String value;
}


