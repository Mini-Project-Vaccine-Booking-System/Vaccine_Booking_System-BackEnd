package com.example.demo.security;

import java.security.Key;

import com.example.demo.service.UserDetailsImpl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(Authentication auth) {
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();

        return Jwts.builder()
            .setId(user.getId().toString())
            .setSubject(user.getUsername())
            .signWith(key)
            .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }
        catch(MalformedJwtException ex){
            log.error("Invalid Jwt Token: {}", ex.getMessage());
        }catch(UnsupportedJwtException ex){
            log.error("Unsupported Jwt Token: {}", ex.getMessage());
        }catch(ExpiredJwtException ex){
            log.error("Expired Jwt Token: {}", ex.getMessage());
        }catch(IllegalArgumentException ex){
            log.error("Jwt claim string is empty: {}", ex.getMessage());
        }
        return false;
    }

    public String getUsername(String token) {
       return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }
    public String getId(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getId();
    }

}
