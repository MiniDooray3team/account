package com.nhnacademy.springboot.accountapi.service.impl;

import com.nhnacademy.springboot.accountapi.domain.Member;
import com.nhnacademy.springboot.accountapi.domain.MemberStatus;
import com.nhnacademy.springboot.accountapi.repository.MemberRepository;
import com.nhnacademy.springboot.accountapi.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
    public void updateMemberStatus(Long memberId, int statusId) throws MemberNotFoundException {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            MemberStatus memberStatus = new MemberStatus();
            memberStatus.setStatusId(statusId);
            member.setMemberStatus(memberStatus);
            memberRepository.save(member);
        } else {
            throw new MemberNotFoundException("Id가 있는 회원을 찾을 수 없습니다. : " + memberId);
        }
    }

    @Override
    public Optional<Member> findMemberByMemberIdAndPassword(String memberId, String password) {
        return memberRepository.findByMemberIdAndPassword(memberId, password);
    }
}

