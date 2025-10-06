package com.ankitregmi.ecommerce.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Configuration
public class SecurityConfig {

    @Value("${okta.oauth2.issuer}")
    private String issuer;

    @Value("${okta.oauth2.audience}")
    private String audience;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        JwtAuthenticationConverter jwtAuthConverter = new JwtAuthenticationConverter();

        http
                // Disable CSRF since we are using token-based authentication
                .csrf(csrf -> csrf.disable())

                // Enable CORS for frontend communication
                .cors(cors -> {})

                // Make the API stateless (no HTTP session)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Authorization rules
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints (no login required)
                        .requestMatchers(
                                "/api/products/**",
                                "/api/product-category/**"
                        ).permitAll()

                        // Protected endpoints (require valid JWT)
                        .requestMatchers(
                                "/api/orders/**",
                                "/api/checkout/**"
                        ).authenticated()

                        // Allow everything else
                        .anyRequest().permitAll()
                )

                // ✅ Modern JWT configuration (non-deprecated)
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter))
                );

        return http.build();
    }

    // ✅ JWT Decoder Bean (Auth0 / Okta)
    @Bean
    public JwtDecoder jwtDecoder() {
        System.out.println("🔐 Building JwtDecoder for issuer = " + issuer);
        return NimbusJwtDecoder.withJwkSetUri(issuer + ".well-known/jwks.json").build();
    }
}
