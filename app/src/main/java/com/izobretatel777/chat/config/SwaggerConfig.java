package com.izobretatel777.chat.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title(":Chat! OpenApi reference")
                        .version("V1.0")
                        .description("API documentation for :Chat!")
                        .license(new License().name("Licence").url("https://springdoc.org")));
    }

    @Bean
    public GroupedOpenApi MessagingApi() {
        return GroupedOpenApi.builder()
                .group("Messaging API")
                .pathsToMatch(
                        "/chats/**",
                        "/contacts/**",
                        "/messages/**",
                        "/send/**"
                )
                .build();
    }

    @Bean
    public GroupedOpenApi UserManagementAPI() {
        return GroupedOpenApi.builder()
                .group("User management API")
                .pathsToMatch(
                        "/users/**"
                )
                .pathsToExclude(
                        "/users/authenticate/**",
                        "/users/register/**"
                )
                .build();
    }

    @Bean
    public GroupedOpenApi LoginRegistrationAPI() {
        return GroupedOpenApi.builder()
                .group("Login and Registration API")
                .pathsToMatch(
                        "/users/authenticate/**",
                        "/users/register/**"
                )
                .build();
    }
}