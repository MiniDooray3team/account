package com.nhnacademy.springboot.accountapi.controller;

import com.nhnacademy.springboot.accountapi.domain.Member;
import com.nhnacademy.springboot.accountapi.service.MemberService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberService memberService;

    //@Order(1)
    @Test
    public void testGetAllMembers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/account/members"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    // 아래 테스트 코드 에러 내용 [MySQL, JDBC 드라이버 로드 시 이전 버전의 클래스인 com.mysql.jdbc.Driver를 사용하고 있다는 경고]
    @Test
    public void testGetMemberById() throws Exception {
        Member member = new Member();
        member.setId(1L);
        member.setMemberId("test");
        member.setEmail("nhnacademy@dooray.com");
        memberService.createMember(member);
//        given()
        mockMvc.perform(MockMvcRequestBuilders.get("/account/members")) // 회원 1명을 조회하는 의미("/account/members/1")
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testcreateMember() throws Exception {
        String requestBody = "{\"memberId\":\"test\", \"email\":\"nhnacademy@dooray.com\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/account/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void testdeleteMember() throws Exception {
        Member member = new Member();
        //member.setId(1L);
        member.setMemberId("test");

        memberService.createMember(member);

        mockMvc.perform(MockMvcRequestBuilders.delete("account/members/1"))
                .andExpect(status().isNoContent());
    }
}
