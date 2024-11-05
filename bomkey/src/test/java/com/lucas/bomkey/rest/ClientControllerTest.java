package com.lucas.bomkey.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucas.bomkey.client.ClientInfo;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String CLIENT_URL = "/client/v1";

    @Test
    @DisplayName("client Info Save Test")
    void saveTest() throws Exception {
        ClientInfo info = new ClientInfo();
        info.setClientDesc("Test Desc");
        info.setClientName("Test Name");
        info.setClientUrl("Test Url");

        // 객체를 JSON으로 변환
        String jsonContent = objectMapper.writeValueAsString(info);

        mockMvc.perform(post(CLIENT_URL)
                        .contentType(MediaType.APPLICATION_JSON) // JSON 요청 타입 설정
                        .content(jsonContent)) // JSON 바디 추가
                .andExpect(status().isOk()) // 응답 상태 확인
                .andExpect(result -> {
                    var response = result.getResponse().getContentAsString();
                    System.out.println("Response Body: " + response);

                    ClientInfo responseInfo = objectMapper.readValue(response, ClientInfo.class);

                    assertNotNull(response);
                    assert responseInfo.getId() != null;
                });
    }

    @Test
    @DisplayName("client Info Update Test")
    void updateClient() throws Exception {
        ClientInfo info = new ClientInfo();
        info.setId(1L);
        info.setClientDesc("Test Desc");
        info.setClientName("Test Name");
        info.setClientUrl("Test Url");
        info.setClientSecret(RandomStringUtils.randomAlphabetic(15));

        String jsonContent = objectMapper.writeValueAsString(info);

        mockMvc.perform(post(CLIENT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    var response = result.getResponse().getContentAsString();
                    System.out.println("Response Body: " + response);

                    ClientInfo responseInfo = objectMapper.readValue(response, ClientInfo.class);

                    assertNotNull(response);
                    assert responseInfo.getId() != null;
                    assertEquals(info.getClientName(), responseInfo.getClientName());
                });
    }

    @Test
    @DisplayName("client Info findAll Test")
    void findAll() throws Exception {
        mockMvc.perform(get(CLIENT_URL))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    var response = result.getResponse().getContentAsString();
                    System.out.println("Response Body: " + response);

                    List<ClientInfo> responseList = objectMapper.readValue(response, new TypeReference<List<ClientInfo>>() {});
                    for (int i = 0; i < responseList.size(); i++) {
                        System.out.println(i+ ": "+ responseList.get(i));
                    }

                    assert responseList.size() > 0;
                });
    }

    @Test
    @DisplayName("client Info Paging Test")
    void findPaging() throws Exception {
        mockMvc.perform(get(CLIENT_URL.concat("/page"))
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "id,DESC")
                )
                .andExpect(status().isOk())
                .andExpect(result -> {
                    var response = result.getResponse().getContentAsString();
                    System.out.println("Response Body: " + response);

                    assertNotNull(response);
                });
    }
}