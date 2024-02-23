package com.nhnacademy.springboot.accountapi.exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException() {
        super("Id와 비밀번호가 일치하는 회원을 찾을 수 없습니다.");
    }
}
