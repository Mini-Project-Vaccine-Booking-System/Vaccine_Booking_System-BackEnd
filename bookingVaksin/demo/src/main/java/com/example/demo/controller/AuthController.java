package com.example.demo.controller;


import com.example.demo.payload.TokenResponse;
import com.example.demo.payload.UsernamePassword;
import com.example.demo.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsernamePassword req) {
        authService.register(req);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody UsernamePassword req) {
        TokenResponse token = authService.generateToken(req);
        return ResponseEntity.ok(token);
    }
}
