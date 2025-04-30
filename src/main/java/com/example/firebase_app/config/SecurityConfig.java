package com.example.firebase_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/**").permitAll() // Allow unauthenticated access to /api/question/add
                    .anyRequest().authenticated() // Require authentication for all other requests
            )
            .httpBasic(withDefaults()) // Use HTTP Basic authentication for other endpoints
            .csrf(csrf -> csrf.disable()); // Disable CSRF for simplicity, consider enabling it with proper configuration for production

        return http.build();
    }
}
