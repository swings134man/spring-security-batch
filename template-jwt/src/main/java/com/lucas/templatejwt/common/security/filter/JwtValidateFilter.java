package com.lucas.templatejwt.common.security.filter;

import com.lucas.templatejwt.common.security.jwt.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtValidateFilter extends OncePerRequestFilter {

    private final JwtProvider provider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String jwt = provider.resolveToken(request);
        String jwt = request.getHeader("Authorization");

        if(jwt != null) {
            provider.validateToken(jwt);
        }

        filterChain.doFilter(request, response);
    }
}
