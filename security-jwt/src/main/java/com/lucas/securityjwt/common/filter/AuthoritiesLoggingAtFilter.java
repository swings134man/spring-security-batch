package com.lucas.securityjwt.common.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * AuthoritiesLoggingAtFilter Class - Filter for Authorities Logging
 * BasicAuthenticationFilter 과 같은 위치에 실행되는 Filter
 *
 * 인증중임을 로깅하는 Filter
 */
@Slf4j
public class AuthoritiesLoggingAtFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("### User Authentication Is Processing ###");

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
