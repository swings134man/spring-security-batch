package com.lucas.bomkey.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.stereotype.Component;

/**
 * JWT Customizer
 * - Token Type AT Only (Access Token) Customization
 * - Add Custom Claim
 */
@Component
public class JwtCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {

    // OAuth_Client.id == SecurityContextHolder.getContext().getAuthentication().getName() => user.host()
    private String returnClientId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            return authentication.getName();
        }
        return "unknown";
    }

    @Override
    public void customize(JwtEncodingContext context) {
        // Token Type AT Only (Access Token) Customization
        if(context.getTokenType().getValue().equals("access_token")) {
            context.getClaims().claim("user_host", returnClientId());

            // Exp
//            Instant now = Instant.now();
//            Instant expTime = now.plus(60, ChronoUnit.MINUTES); // 1Hour
//            context.getClaims().expiresAt(expTime);
        }
    }
}
