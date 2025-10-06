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
                // Disable CSRF for REST APIs
                .csrf(csrf -> csrf.disable())

                // Enable CORS
                .cors(cors -> {})

                // Make session stateless
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Authorization rules
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers(
                                "/api/products/**",
                                "/api/product-category/**"
                        ).permitAll()

                        // Protected endpoints (require login)
                        .requestMatchers(
                                "/api/orders/**",
                                "/api/checkout/**"
                        ).authenticated()

                        // Everything else allowed
                        .anyRequest().permitAll()
                )

                // ✅ Non-deprecated JWT config
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
