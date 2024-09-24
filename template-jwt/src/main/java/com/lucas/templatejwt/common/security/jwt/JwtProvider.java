package com.lucas.templatejwt.common.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class JwtProvider {

    private final String JWT_KEY = "ThisISForTestJwtKeyCode";


    public String generateToken() {
        log.info("Generating token");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(null != authentication){
            SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));

            String jwt = Jwts.builder()
                    .setIssuer("Lucas boot").setSubject("JWT Token")
                    .claim("username", authentication.getName())
                    .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + 30000000)) // 8 hours(ms) = 30000000, 30000=30s
                    .signWith(key).compact();

            log.info("Generated token: {}", jwt);
            return jwt;
        }else {
            return null;
        }
    }

    public void validateToken(String jwtToken) {
        if(null != jwtToken) {
            try {
                SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));

                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(jwtToken)
                        .getBody();

                String username = String.valueOf(claims.get("username"));
                String authorities = (String) claims.get("authorities");

                Authentication auth = new UsernamePasswordAuthenticationToken(username, null, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));

                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception exception) {
                throw new BadCredentialsException("Invalid Token received!");
            }
        }
    }

//    public String resolveToken(HttpServletRequest request) {
//        String headerKey = HttpHeaders.AUTHORIZATION;
//        String bearerToken = request.getHeader(headerKey);
//        if (StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7, bearerToken.length());
//        }
//        return null;
//    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }
}
