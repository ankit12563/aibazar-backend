package com.ankitregmi.ecommerce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF for APIs
                .csrf(csrf -> csrf.disable())

                // Enable CORS
                .cors(cors -> {})

                // Authorize requests
                .authorizeHttpRequests(auth -> auth
                        // Allow all REST API endpoints (backend REST + checkout)
                        .requestMatchers("/api/**").permitAll()
                        // Allow static resources (favicon, etc.)
                        .requestMatchers(
                                new AntPathRequestMatcher("/"),
                                new AntPathRequestMatcher("/favicon.ico"),
                                new AntPathRequestMatcher("/error")
                        ).permitAll()
                        // Everything else must be authenticated
                        .anyRequest().permitAll()
                )

                // Disable frame and login page defaults
                .headers(headers -> headers.frameOptions().disable());

        return http.build();
    }
}
