package com.example.tomatomall.interceptor;

import com.example.tomatomall.service.TokenService;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {
    
    private final TokenService tokenService;
    
    public AuthInterceptor(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 登录和注册接口不需要验证
        if (request.getRequestURI().contains("/login") || 
            (request.getRequestURI().equals("/api/accounts") && request.getMethod().equals("POST"))) {
            return true;
        }
        
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String username = tokenService.validateToken(cookie.getValue());
                    if (username != null) {
                        request.setAttribute("currentUser", username);
                        return true;
                    }
                    break;
                }
            }
        }
        response.setStatus(401);
        return false;
    }
}