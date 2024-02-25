package com.nhnacademy.springboot.accountapi.service.impl;

import com.nhnacademy.springboot.accountapi.domain.Member;
import com.nhnacademy.springboot.accountapi.domain.MemberStatus;
import com.nhnacademy.springboot.accountapi.exception.MemberNotFoundException;
import com.nhnacademy.springboot.accountapi.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class MemberServiceImplTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberService;

    @Test
    void updateMemberStatus_existingMember() {
        // Given
        long memberId = 1L;
        int statusId = 1;
        Member member = new Member();
        member.setId(memberId);
        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));
        when(memberRepository.save(member)).thenReturn(member);

        // When
//        assertDoesNotThrow(() -> memberService.updateMemberStatus(memberId, statusId));

        // Then
//        assertEquals(statusId, member.getMemberStatus().getStatusId());
    }

    @Test
    void updateMemberStatus_nonExistingMember() {
        // Given
        long memberId = 1L;
        int statusId = 1;
        when(memberRepository.findById(memberId)).thenReturn(Optional.empty());

        // When & Then
//        assertThrows(MemberNotFoundException.class, () -> memberService.updateMemberStatus(memberId, statusId));
    }
}
