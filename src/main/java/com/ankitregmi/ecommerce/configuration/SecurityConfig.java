package com.ankitregmi.ecommerce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection for API requests
                .csrf(csrf -> csrf.disable())

                // Authorize API requests
                .authorizeHttpRequests(auth -> auth
                        // Permit all public API endpoints
                        .requestMatchers("/api/**").permitAll()
                        // Permit any other request (like favicon, docs, etc.)
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}
