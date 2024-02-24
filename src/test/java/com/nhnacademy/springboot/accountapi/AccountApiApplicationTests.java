package com.nhnacademy.springboot.accountapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class AccountApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    public AccountApiApplicationTests(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void testGetAllMembers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/account/members")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetMemberById() throws Exception {

    }

    @Test
    void testGetCreatMember() throws Exception {

    }

    @Test
    void testGetDeleteMember() throws Exception {

    }



    //    @Test
//    void contextLoads() {
//    }

}
