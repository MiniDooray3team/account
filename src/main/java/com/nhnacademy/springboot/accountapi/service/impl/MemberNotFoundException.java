package com.nhnacademy.springboot.accountapi.service.impl;

// 예외 처리 부분 (MemberServiceImpl.java)
public class MemberNotFoundException extends Throwable {
    public MemberNotFoundException(String message) {
        super(message);
    }
}
