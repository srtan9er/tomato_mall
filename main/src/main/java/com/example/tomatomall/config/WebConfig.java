package com.example.tomatomall.config;

import com.example.tomatomall.interceptor.AuthInterceptor;
import com.example.tomatomall.service.TokenService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    private final TokenService tokenService;
    
    public WebConfig(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor(tokenService))
                .addPathPatterns("/api/accounts/**");
    }
}