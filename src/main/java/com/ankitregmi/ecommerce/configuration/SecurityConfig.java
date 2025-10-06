package com.ankitregmi.ecommerce.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Value("${okta.oauth2.issuer}")
    private String issuer;

    @Value("${okta.oauth2.audience}")
    private String audience;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF for API
                .csrf(csrf -> csrf.disable())

                // Enable CORS
                .cors(cors -> {})

                // Stateless sessions for REST API
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Define routes
                .authorizeHttpRequests(auth -> auth
                        // Public routes
                        .requestMatchers(
                                "/api/products/**",
                                "/api/product-category/**"
                        ).permitAll()

                        // Protected routes
                        .requestMatchers(
                                "/api/orders/**",
                                "/api/checkout/**"
                        ).authenticated()

                        // Allow all else
                        .anyRequest().permitAll()
                )

                // ✅ Enable JWT Authentication (Auth0)
                .oauth2ResourceServer(oauth2 -> oauth2.jwt());

        return http.build();
    }

    // ✅ Auth0 JWT Decoder bean
    @Bean
    public JwtDecoder jwtDecoder() {
        System.out.println("🔐 Building JwtDecoder for issuer = " + issuer);
        return NimbusJwtDecoder.withJwkSetUri(issuer + ".well-known/jwks.json").build();
    }
}
