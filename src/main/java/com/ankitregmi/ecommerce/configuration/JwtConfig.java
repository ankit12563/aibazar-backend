package com.ankitregmi.ecommerce.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
public class JwtConfig {

    @Value("${okta.oauth2.issuer}")
    private String issuer;

    @Bean
    public JwtDecoder jwtDecoder() {
        System.out.println("✅ Building JwtDecoder with issuer: " + issuer);
        // This builds a decoder manually so Spring Boot won’t fail at startup
        return NimbusJwtDecoder.withIssuerLocation(issuer).build();
    }
}
