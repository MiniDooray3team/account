package com.nhnacademy.springboot.accountapi.controller;

import com.nhnacademy.springboot.accountapi.domain.Member;
import com.nhnacademy.springboot.accountapi.service.MemberService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

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
    TestEntityManager testEntityManager;

    @Autowired
    private MemberService memberService;

    //@Order(1)
    @Test
    public void testGetAllMembers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/account/members"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    // 멤버 status가 없어서 그렇고 또한 db에서 null을 넣어줘야하는데 null값이 없으니 db에서 거절을 해버린다. 따라서 password랑 createdAt(LocalDateTime.now()를 추가해줬다.
    @Test
    public void testGetMemberById() throws Exception {
        Member member = new Member();
        member.setId(1L);
        member.setPassword("adsdasd");
        member.setMemberId("test");
        member.setEmail("nhnacademy@dooray.com");
        member.setCreatedAt(LocalDateTime.now());
        memberService.createMember(member);
//        given()
        mockMvc.perform(MockMvcRequestBuilders.get("/account/members/1")) // 회원 1명을 조회하는 의미("/account/members/1")
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
