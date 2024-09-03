package com.lucas.securityjwt.common.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

/**
 * AuthoritiesLoggingAfterFilter Class - Filter for Authorities Logging
 * BasicAuthenticationFilter 이후에 실행되는 Filter
 *
 * User 에 관련된 권한 및, 몇몇 정보들을 Logging 하는 Filter
 */
@Slf4j
public class AuthoritiesLoggingAfterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(null != authentication) {
            log.info("User: {} is Authentication Success has Authorities: {}", authentication.getName(), authentication.getAuthorities().toString());
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }//doFilter
}
