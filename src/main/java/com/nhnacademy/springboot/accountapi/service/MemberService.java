package com.nhnacademy.springboot.accountapi.service;

import com.nhnacademy.springboot.accountapi.domain.Member;

import java.util.List;

public interface MemberService {

    List<Member> getAllMembers();

    Member getMemberById(Long id);

    Member createMember(Member member);

    Member updateMember(Member member);

    void deleteMember(Long id);
}

