package com.apricot.back.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handleCustomException(CustomException customException) {
        ErrorCode errorCode = customException.getErrorCode();
        return new ResponseEntity<>(errorCode.getMessage(), HttpStatus.valueOf(errorCode.getCode()));
    }
}
