package com.nhnacademy.springboot.accountapi.advice;

import com.nhnacademy.springboot.accountapi.exception.MemberNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestControllerAdvicesTest {

    private final RestControllerAdvices restControllerAdvices = new RestControllerAdvices();

    @Test
    public void testHandleMemberNotFoundException() {
        // Given
        MemberNotFoundException exception = new MemberNotFoundException("Id와 비밀번호가 일치하는 회원을 찾을 수 없습니다.");

        // When
        ResponseEntity<Map<String, String>> responseEntity = restControllerAdvices.handleMemberNotFoundException(exception);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Id와 비밀번호가 일치하는 회원을 찾을 수 없습니다.", responseEntity.getBody().get("message"));
    }
}
