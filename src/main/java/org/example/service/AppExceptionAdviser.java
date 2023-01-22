package org.example.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionAdviser {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<AppExceptionResponse> handleException(AppException e) {
        AppExceptionResponse response = new AppExceptionResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
