package com.lucas.bomkey.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucas.bomkey.domains.oauth_client.OAuthClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OAuthClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String CLIENT_URL = "/client/v1";

    @Test
    @DisplayName("client Info Save Test")
    void saveTest() throws Exception {
        OAuthClient info = new OAuthClient();
        info.setClientId("oidc-client");
        info.setClientSecret("secret");
        info.setClientName("test oidc client");
        info.setRedirectUri("http://localhost:3000");
        info.setPostLogoutRedirectUri("http://localhost:3000");
        info.setRequireAuthorizationConsent(true);

        // 객체를 JSON으로 변환
        String jsonContent = objectMapper.writeValueAsString(info);

        mockMvc.perform(post(CLIENT_URL)
                        .contentType(MediaType.APPLICATION_JSON) // JSON 요청 타입 설정
                        .content(jsonContent)) // JSON 바디 추가
                .andExpect(status().isOk()) // 응답 상태 확인
                .andExpect(result -> {
                    var response = result.getResponse().getContentAsString();
                    System.out.println("Response Body: " + response);

                    OAuthClient responseInfo = objectMapper.readValue(response, OAuthClient.class);

                    assertNotNull(response);
                    assert responseInfo.getId() != null;
                });
    }

    @Test
    @DisplayName("client Info Update Test")
    void updateClient() throws Exception {
        OAuthClient info = new OAuthClient();
        info.setClientId("oidc-client");
        info.setClientSecret("secret");
        info.setClientName("test oidc client111");
        info.setRedirectUri("http://localhost:3000");
        info.setPostLogoutRedirectUri("http://localhost:3000");
        info.setRequireAuthorizationConsent(true);

        String jsonContent = objectMapper.writeValueAsString(info);

        mockMvc.perform(post(CLIENT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    var response = result.getResponse().getContentAsString();
                    System.out.println("Response Body: " + response);

                    OAuthClient responseInfo = objectMapper.readValue(response, OAuthClient.class);

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

                    List<OAuthClient> responseList = objectMapper.readValue(response, new TypeReference<List<OAuthClient>>() {});
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
    
    // --------------------------------------------

    @Test
    @DisplayName("Rsa Key Gen API")
    void rsaKeySave() throws Exception {
        String identifier = "bomkey";
        mockMvc.perform(post(CLIENT_URL.concat("/").concat(identifier).concat("/keys"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    var response = result.getResponse().getContentAsString();
                    System.out.println("Response Body: " + response);
                });
    }
}