package com.lucas.bomkey.domains.oauth_client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuthClientService {

    private final OauthClientRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Using SecurityConfig RegisteredClientRepository findByClientId's implementation Methods
     * Default OAuthClient Info Change to RegisteredClient
     * @param clientId
     * @return RegisteredClient
     */
    public RegisteredClient loadClientByClientId(String clientId) {
       OAuthClient result = repository.findByClientId(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client not Found Exception: " + clientId));

        return loadClientByResult(result);
    }

    /**
     * Using SecurityConfig
     * RegisteredClientRepository finById's implementation Methods
     * @param id
     * @return RegisteredClient
     */
    @Transactional
    public RegisteredClient findByIdString(String id) {
        Long longId = Long.valueOf(id);
        OAuthClient result = repository.findById(longId).get();

        return loadClientByResult(result);
    }

    /**
     * OAuthClient to RegisteredClient Converter
     * @param result
     * @return RegisteredClient
     */
    private RegisteredClient loadClientByResult(OAuthClient result) {
        return RegisteredClient.withId(result.getClientId())
                .clientId(result.getClientId())
                .clientSecret(passwordEncoder.encode(result.getClientSecret()))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri(result.getRedirectUri())
                .postLogoutRedirectUri(result.getPostLogoutRedirectUri())
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(result.isRequireAuthorizationConsent()).build())
                .build();
    }

    /**
     * Get OriginUris from OAuthClient
     * @return
     */
    public List<String> getOriginUris() {
        return repository.findOrigins();
    }

    @Transactional
    public OAuthClient saveClient(OAuthClient client) {
        log.info("New Client Save: {}", client);
        if(client.getId() == null){
            return repository.save(client);
        }else {
            OAuthClient byId = repository.findById(client.getId()).get();
            byId.setClientId(client.getClientId());
            byId.setClientSecret(client.getClientSecret());
            byId.setClientName(client.getClientName());
            byId.setRedirectUri(client.getRedirectUri());
            byId.setPostLogoutRedirectUri(client.getPostLogoutRedirectUri());
            byId.setRequireAuthorizationConsent(client.isRequireAuthorizationConsent());
            return repository.save(byId);
        }
    }

    @Transactional
    public boolean deleteClient(Long clientId) {
        repository.deleteById(clientId);
        return !repository.existsById(clientId);
    }

    @Transactional(readOnly = true)
    public Page<OAuthClient> getClient(Pageable pageable) {
        return repository.findAll(pageable);
    }

}
