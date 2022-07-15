package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.dto.UserDTO;
import com.example.demo.response.TokenResponse;
import com.example.demo.service.AuthService;
import com.example.demo.util.*;
import com.example.demo.constant.*;

import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    
    @PostMapping(value = "/regHealth")
    public ResponseEntity<?> registerAdmin(@RequestBody UserDTO req) {
        try {
            User regAdmin = authService.registerAdmin(req);
            return ResponseUtil.build("REGISTRATION HEALTH FACILITY SUCCESS",AppConstant.ResponseCode.SUCCESS, regAdmin, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/regUser")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO req) {
        try {
            User regUser= authService.registerUser(req);
            return ResponseUtil.build("REGISTRATION USER SUCCESS",AppConstant.ResponseCode.SUCCESS, regUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> generateToken(@RequestBody UserDTO req) {
        
        try{TokenResponse token = authService.generateToken(req);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Authorization", token.getToken());
            return ResponseEntity.ok().headers(responseHeaders).body(token);
        }catch(Exception e){return ResponseUtil.build("LOGIN ERROR",AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.UNAUTHORIZED);}
        
        
    }

}
