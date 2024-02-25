package com.nhnacademy.springboot.accountapi.service;

import com.nhnacademy.springboot.accountapi.domain.Member;
import com.nhnacademy.springboot.accountapi.domain.MemberStatus;
import com.nhnacademy.springboot.accountapi.dto.MemberDto;
import com.nhnacademy.springboot.accountapi.exception.MemberNotFoundException;

import java.util.List;

public interface MemberService {

    List<Member> getAllMembers();

    Member getMemberById(Long id);

    Member createMember(Member member);

    Member createMember(MemberDto memberDto);

    Member updateMember(Member member);

    void deleteMember(Long id);

    void updateMemberStatus(Long memberId, MemberStatus status) throws MemberNotFoundException;

    Member findMemberByMemberIdAndPassword(String memberId, String password);
}

