package com.lucas.bomkey.domains.oauth_client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OauthClientRepository extends JpaRepository<OAuthClient, Long> {
    Optional<OAuthClient> findByClientId(String clientId);

    @Query("SELECT DISTINCT o.redirectUri FROM OAuthClient o")
    List<String> findOrigins();
}
