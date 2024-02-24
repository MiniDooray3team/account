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

    @Test
    void testGetAllMembers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/account/members")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetMemberById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/account/members/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testCreateMember() throws Exception {
        String requestBody = "{\"id\":1,\"memberId\":\"test\",\"email\":\"nhnacademy@dooray.com\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/account/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void testDeleteMember() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/account/members/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    // Add more integration tests for other endpoints as needed

}
