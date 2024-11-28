package com.lucas.bomkey.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;

/**
 * Role Hierarchy Configuration
 * ROLE 기반 Method Security를 사용할 때, Role간의 상하관계를 설정하는 클래스
 *
 * SecurityConfig: @EnableGlobalMethodSecurity(prePostEnabled = true)
 * sample: @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
 */
@Configuration
public class RoleHierarchyConfig {
    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_ADMIN > ROLE_MANAGER \n ROLE_MANAGER > ROLE_USER";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }
}
