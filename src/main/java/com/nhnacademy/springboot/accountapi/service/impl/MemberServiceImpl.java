package com.nhnacademy.springboot.accountapi.service.impl;

import com.nhnacademy.springboot.accountapi.domain.Member;
import com.nhnacademy.springboot.accountapi.domain.MemberStatus;
import com.nhnacademy.springboot.accountapi.dto.MemberDto;
import com.nhnacademy.springboot.accountapi.exception.MemberNotFoundException;
import com.nhnacademy.springboot.accountapi.repository.MemberRepository;
import com.nhnacademy.springboot.accountapi.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member createMember(MemberDto memberDto) {
        Member member = new Member();
        member.setMemberId(memberDto.getMemberId());
        member.setEmail(memberDto.getEmail());
        member.setPassword(memberDto.getPassword());
        member.setStatus(MemberStatus.ACTIVE); // 새로운 회원 생성 시 기본 상태 설정

        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(Member member) {
        if (memberRepository.existsById(member.getId())) {
            return memberRepository.save(member);
        }
        return null;
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public void updateMemberStatus(Long memberId, MemberStatus status) throws MemberNotFoundException {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            member.setStatus(status); // MemberStatus 타입으로 변경
            memberRepository.save(member);
        } else {
            throw new MemberNotFoundException("ID가 있는 회원을 찾을 수 없습니다. ID: " + memberId);
        }
    }

    @Override
    public Member findMemberByMemberIdAndPassword(String memberId, String password) {
        log.debug("findMemberByMemberIdAndPassword: memberId={}, password={}", memberId, password);
        return memberRepository.findByMemberIdAndPassword(memberId, password).orElseThrow();
    }
}

