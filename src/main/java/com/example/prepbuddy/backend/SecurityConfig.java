package com.example.prepbuddy.backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf((csrf) -> csrf.disable())  // Disable CSRF protection
            .authorizeHttpRequests((authz) -> authz
                .anyRequest().permitAll()  // Allow all requests without login
            )
            .httpBasic((basic) -> basic.disable()); // Disable basic auth
        return http.build();
    }
}
