package com.lucas.templatejwt.common.security.filter;

import com.lucas.templatejwt.common.security.jwt.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtGenFilter extends OncePerRequestFilter {

    private final JwtProvider provider;

    public JwtGenFilter(JwtProvider provider) {
        this.provider = provider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = provider.generateToken();

        setJwtToResponse(response, jwt);

        filterChain.doFilter(request, response);
    }

    public void setJwtToResponse(HttpServletResponse response, String jwt) {
        response.setHeader(HttpHeaders.AUTHORIZATION, jwt);
    }

}
