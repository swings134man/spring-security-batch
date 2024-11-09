package com.lucas.bomkey.oauth_client;

import com.lucas.bomkey.config.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "oauth_clients")
@Getter @Setter @ToString
public class OAuthClient extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientId;
    private String clientSecret;
    private String clientName;
    private String redirectUri;
    private String postLogoutRedirectUri;
    private boolean requireAuthorizationConsent; // default true
}
