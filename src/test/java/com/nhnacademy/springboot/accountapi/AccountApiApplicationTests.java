package com.nhnacademy.springboot.accountapi;

import com.nhnacademy.springboot.accountapi.domain.Member;
import com.nhnacademy.springboot.accountapi.dto.MemberDto;
import com.nhnacademy.springboot.accountapi.repository.MemberRepository;
import com.nhnacademy.springboot.accountapi.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AccountApiApplicationTests {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void testMemberLifecycle() {
        // 새로운 회원 생성
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberId("testMember");
        memberDto.setEmail("test@example.com");
        memberDto.setPassword("testPassword");

        Member createdMember = memberService.createMember(memberDto);
        assertNotNull(createdMember.getId());

        // 생성된 회원 조회
        Member foundMember = memberService.getMemberById(createdMember.getId());
        assertNotNull(foundMember);
        assertEquals("testMember", foundMember.getMemberId());
        assertEquals("test@example.com", foundMember.getEmail());

        // 회원 정보 수정
        foundMember.setEmail("updated@example.com");
        memberService.updateMember(foundMember);

        // 수정된 회원 조회
        Member updatedMember = memberService.getMemberById(foundMember.getId());
        assertEquals("updated@example.com", updatedMember.getEmail());

        // 회원 삭제
        memberService.deleteMember(updatedMember.getId());

        // 삭제된 회원 조회
        Member deletedMember = memberService.getMemberById(updatedMember.getId());
        assertNull(deletedMember);
    }
}
