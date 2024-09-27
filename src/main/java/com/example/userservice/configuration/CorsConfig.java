package com.example.userservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Apply CORS for all endpoints and allow requests from any origin
        registry.addMapping("/**")
                .allowedOrigins("*")  // Allow requests from all origins
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allow HTTP methods
                .allowedHeaders("*")  // Allow all headers
                // Do not allow credentials
                .maxAge(3600);  // Set how long the response should be cached by the clients
    }
}
