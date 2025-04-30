package com.example.firebase_app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Apply CORS to /api/** paths
                .allowedOrigins("*") // Allow all origins
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow specific methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(false); // Allow credentials if needed (e.g., cookies), set to false if not
    }
}