//package com.ankitregmi.ecommerce.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                // Disable CSRF for APIs
//                .csrf(csrf -> csrf.disable())
//
//                // Enable CORS
//                .cors(cors -> {})
//
//                // Authorize requests
//                .authorizeHttpRequests(auth -> auth
//                        // Allow all REST API endpoints (backend REST + checkout)
//                        .requestMatchers("/api/**").permitAll()
//                        // Allow static resources (favicon, etc.)
//                        .requestMatchers(
//                                new AntPathRequestMatcher("/"),
//                                new AntPathRequestMatcher("/favicon.ico"),
//                                new AntPathRequestMatcher("/error")
//                        ).permitAll()
//                        // Everything else must be authenticated
//                        .anyRequest().permitAll()
//                )
//
//                // Disable frame and login page defaults
//                .headers(headers -> headers.frameOptions().disable());
//
//        return http.build();
//    }
//}
package com.ankitregmi.ecommerce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Disable CSRF because we use JWTs
                .csrf(csrf -> csrf.disable())

                // Enable CORS (handled by GlobalCorsConfig)
                .cors(cors -> {})

                // Stateless session management
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Authorization rules
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers(
                                "/api/products/**",
                                "/api/product-category/**"
                        ).permitAll()

                        // Protected endpoints (require JWT)
                        .requestMatchers(
                                "/api/checkout/**",
                                "/api/orders/**"
                        ).authenticated()

                        // everything else allowed
                        .anyRequest().permitAll()
                )

                // ✅ This line tells Spring: "use OAuth2 JWT, but don't block public endpoints"
                .oauth2ResourceServer(oauth2 -> oauth2.jwt())

                // disable login page redirects (API only)
                .formLogin(login -> login.disable())
                .httpBasic(basic -> basic.disable());

        return http.build();
    }
}
