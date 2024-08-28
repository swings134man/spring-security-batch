package com.lucas.securitybasic.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "Security Basic APIs",
                version = "1.0",
                description = "REST API Docs"
        )
)
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    // http://localhost:8080/swagger-ui/index.html

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("API")
                .pathsToMatch("/**")
                .build();
    }
}
