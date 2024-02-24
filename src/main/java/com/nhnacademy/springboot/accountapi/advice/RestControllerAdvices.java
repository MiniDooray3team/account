package com.nhnacademy.springboot.accountapi.advice;

import com.nhnacademy.springboot.accountapi.exception.MemberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;


@RestControllerAdvice
public class RestControllerAdvices {

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleMemberNotFoundException(MemberNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
    }
}
