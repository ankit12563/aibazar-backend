package com.ankitregmi.ecommerce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalCorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**") // ✅ Allow all API endpoints
                        .allowedOrigins(
                                "http://localhost:4200",        // Angular local
                                "https://aibazzar.netlify.app"  // Deployed frontend
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")                   // ✅ Allow all headers (important for Auth0 tokens)
                        .exposedHeaders("Authorization", "Link", "X-Total-Count") // Optional but helpful
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }
}
