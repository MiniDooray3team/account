package com.nhnacademy.springboot.accountapi.service;

import com.nhnacademy.springboot.accountapi.domain.Member;
import com.nhnacademy.springboot.accountapi.service.impl.MemberNotFoundException;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    List<Member> getAllMembers();

    Member getMemberById(Long id);

    Member createMember(Member member);

    Member updateMember(Member member);

    void deleteMember(Long id);

    void updateMemberStatus(Long memberId, int statusId) throws MemberNotFoundException;

    Optional<Member> findMemberByMemberIdAndPassword(String memberId, String password);
}

