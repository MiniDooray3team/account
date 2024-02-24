package com.nhnacademy.springboot.accountapi.service;

import com.nhnacademy.springboot.accountapi.domain.Member;
import com.nhnacademy.springboot.accountapi.repository.MemberRepository;
import com.nhnacademy.springboot.accountapi.service.impl.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeleteMember() {
        // 주어진 상황
        long memberId = 1L;
        Member member = new Member();
        member.setId(memberId);

        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));

        // 실행
        memberService.deleteMember(memberId);

        // 확인
        verify(memberRepository).delete(member);
    }

}
