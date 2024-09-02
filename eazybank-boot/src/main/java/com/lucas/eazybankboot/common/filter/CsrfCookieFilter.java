package com.lucas.eazybankboot.common.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * CsrfCookieFilter Class
 * CSRF Cookie Filter
 *
 * CSRF Token 값은 Header 안에 존재하게됨. (HeaderName: X-CSRF-TOKEN)
 * Cookie 값은 framework 에서 관리하게 되며, 브라우저, UI에 응답을 해줌
 */
public class CsrfCookieFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

        if(null != csrfToken.getHeaderName()) {
            response.setHeader(csrfToken.getHeaderName(), csrfToken.getToken());
        }

        filterChain.doFilter(request, response);
    }
}
