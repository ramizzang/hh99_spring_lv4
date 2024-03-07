package com.sparta.classapi.global.handler;

import com.sparta.classapi.global.handler.exception.CustomApiException;
import com.sparta.classapi.global.handler.exception.ErrorCode;
import com.sparta.classapi.global.handler.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<Object> handleCustomApiException(CustomApiException e) {
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_TYPE_VALUE);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        return ResponseEntity.badRequest().body(new ResponseDto<>(false, e.getMessage(), null));
    }

//    @ExceptionHandler(CustomValidationException.class)
//    public ResponseEntity<Object> handlerCustomValidationApiException(CustomValidationException e) {
//        return ResponseEntity.badRequest().body(new ResponseDto<>(false, e.getMessage(), e.getErrorMap()));
//    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> processValidationError(MethodArgumentNotValidException e) {
//        return ResponseEntity.badRequest().body(new ResponseDto<>(false, e.getMessage(), null));
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_TYPE_VALUE, e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
