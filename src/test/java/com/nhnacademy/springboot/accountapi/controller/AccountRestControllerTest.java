package com.nhnacademy.springboot.accountapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.springboot.accountapi.domain.Member;
import com.nhnacademy.springboot.accountapi.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(AccountRestController.class)
class AccountRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

    @Test
    public void testGetAllMembers() throws Exception {
        List<Member> members = new ArrayList<>();
        when(memberService.getAllMembers()).thenReturn(members); // 서비스 메서드 mock
        mockMvc.perform(MockMvcRequestBuilders.get("/account/members"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    public void testGetMemberById() throws Exception {
        Member member = new Member();
        member.setId(1L);
        member.setPassword("adsdasd");
        member.setMemberId("test");
        member.setEmail("nhnacademy@dooray.com");
        member.setCreatedAt(LocalDateTime.now());
        when(memberService.getMemberById(1L)).thenReturn(member); // 서비스 메서드 mock
        mockMvc.perform(MockMvcRequestBuilders.get("/account/members/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testcreateMember() throws Exception {
        Member member = new Member();
        member.setId(1L);
        member.setMemberId("test");
        member.setEmail("nhnacademy@dooray.com");
        String requestBody = objectMapper.writeValueAsString(member); // 회원 객체 json 문자열 반환
        when(memberService.createMember(any(Member.class))).thenReturn(member); // 서비스 메서드 mock
        mockMvc.perform(MockMvcRequestBuilders.post("/account/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                        .andExpect(status().isCreated());
    }

    @Test
    public void testdeleteMember() throws Exception {
        Member member = new Member();
        member.setId(1L);
        member.setMemberId("test");
        when(memberService.getMemberById(1L)).thenReturn(member); // 서비스 mock
        mockMvc.perform(MockMvcRequestBuilders.delete("/account/members/1"))
                .andExpect(status().isNoContent());
    }
}
