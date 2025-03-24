package com.example.tomatomall.service;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

@Service
public class TokenService {
    private final Map<String, String> tokenMap = new ConcurrentHashMap<>();
    
    public String generateToken(String username) {
        String token = UUID.randomUUID().toString();
        tokenMap.put(token, username);
        return token;
    }
    
    public String validateToken(String token) {
        return tokenMap.get(token);
    }
    
    public void removeToken(String token) {
        tokenMap.remove(token);
    }
}