package com.nhnacademy.springboot.accountapi.dto;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String memberId;
    private String password;
}
