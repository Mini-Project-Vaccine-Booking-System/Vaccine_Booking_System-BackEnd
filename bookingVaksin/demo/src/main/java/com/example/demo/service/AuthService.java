package com.example.demo.service;

import com.example.demo.payload.TokenResponse;
import com.example.demo.payload.UsernamePassword;

public interface AuthService {
    com.example.demo.entity.User register(UsernamePassword req);
    TokenResponse generateToken(UsernamePassword req);
    
}
