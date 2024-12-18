package com.lucas.bomkey.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucas.bomkey.domains.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    private final String USER_URL = "/user";

    @Test
    void saveUser() throws Exception {
        User user = new User();
        user.setUserEmail("user@test.com");
        user.setPassword("1234");
        user.setUserName("user");
        user.setRole("ROLE_USER");

        String s = objectMapper.writeValueAsString(user);
        mockMvc.perform(post(USER_URL + "/signup")
                        .contentType(MediaType.APPLICATION_JSON) // JSON 요청 타입 설정
                        .content(s)) // JSON 바디 추가
                .andExpect(status().isOk()) // 응답 상태 확인
                .andExpect(result -> {
                    var response = result.getResponse().getContentAsString();
                    System.out.println("Response Body: " + response);

                    assertNotNull(response);
                });
    }

}