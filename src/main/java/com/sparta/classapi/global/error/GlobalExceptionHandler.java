package com.sparta.classapi.global.error;

import com.sparta.classapi.global.common.ResponseDto;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseDto<?> IllegalArgumentException(IllegalArgumentException e){
        return ResponseDto.fail(e.getMessage(),null);
    }
}
