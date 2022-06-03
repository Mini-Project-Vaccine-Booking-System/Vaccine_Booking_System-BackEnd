package com.example.demo.service.impl;

import javax.management.RuntimeErrorException;

import com.example.demo.entity.User;
import com.example.demo.payload.TokenResponse;
import com.example.demo.payload.UsernamePassword;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtProvider;
import com.example.demo.service.AuthService;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User register(UsernamePassword req) {
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        return userRepository.save(user);
    }
    @Override
    public TokenResponse generateToken(UsernamePassword req) {
        try{
            org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    req.getUsername(), 
                    req.getPassword()
                    )
            );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(jwt);
        return tokenResponse;
        } catch (BadCredentialsException e) {
            log.error("Bad Credential", e);
            throw new RuntimeException(e.getMessage(),e);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    
}
