package com.ankitregmi.ecommerce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // disable CSRF for APIs
                .csrf(csrf -> csrf.disable())

                // authorize requests
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll()   // allow all API endpoints
                        .anyRequest().authenticated()             // everything else needs auth
                )

                // disable form login
                .formLogin(form -> form.disable())

                // disable HTTP Basic (optional, unless you want it)
                .httpBasic(httpBasic -> httpBasic.disable());

        return http.build();
    }
}
