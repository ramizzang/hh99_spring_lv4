package com.sparta.classapi.global.handler.exception;

import lombok.Getter;

@Getter
public class CustomApiException extends RuntimeException {

    public CustomApiException(String message) {
        super(message);
    }
}
