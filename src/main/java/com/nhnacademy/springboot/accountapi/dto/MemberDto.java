package com.nhnacademy.springboot.accountapi.dto;

import com.nhnacademy.springboot.accountapi.domain.MemberStatus;

public class MemberDto {
    private String memberId;
    private String email;
    private String password;
    private MemberStatus status;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MemberStatus getStatus() {
        return status;
    }

    public void setStatus(MemberStatus status) {
        this.status = status;
    }
}
