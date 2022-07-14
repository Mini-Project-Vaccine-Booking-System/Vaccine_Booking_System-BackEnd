package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import com.example.demo.constant.AppConstant;
import com.example.demo.entity.User;
import com.example.demo.entity.dto.UserDTO;
import com.example.demo.service.UserService;
import com.example.demo.util.ResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value= "/user")
public class CitizenController {
    @Autowired
    private UserService userService;


    @GetMapping(value = "")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll() {
        try {
            List<User> users =  userService.getAll();
            return ResponseUtil.build("GET All",AppConstant.ResponseCode.SUCCESS, users, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping(value = "/citizen")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<?> getUser() {
        try {
            List<User> users =  userService.getCitizen();
            return ResponseUtil.build("GET USERS",AppConstant.ResponseCode.SUCCESS, users, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCitizen(@PathVariable(value = "id") Long id) {
        try {
            User users =  userService.findById(id);
            return ResponseUtil.build("GET USER DETAIL",AppConstant.ResponseCode.SUCCESS, users, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping(value = "/search/{kota}")
    public ResponseEntity<?> getUserByCity(@PathVariable(value = "kota") String kota)  {
        try {
            List<User> users =  userService.findByCity(kota);
            return ResponseUtil.build("GET USER BY CITY ",AppConstant.ResponseCode.SUCCESS, users, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(), AppConstant.ResponseCode.UNKNOWN_ERROR,null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/health")
    public ResponseEntity<?> getHealth() {
        try {
            List<User> users =  userService.getHealth();
            return ResponseUtil.build("GET HEALTH FACILITIES",AppConstant.ResponseCode.SUCCESS, users, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

          

    @PutMapping(value = "/{id}") 
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO citizen) {
        try {
            User user = userService.updateUser(id, citizen);
            return ResponseUtil.build("KELOMPOK ID " + id + " UPDATED",AppConstant.ResponseCode.SUCCESS, user, HttpStatus.OK);
            } catch (Exception e) {
                return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
            } 
        }
    
    @PutMapping(value = "/health/{id}") 
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateHealth(@PathVariable Long id, @RequestBody UserDTO health) {
        try {
            User user = userService.updateHealth(id, health);
            return ResponseUtil.build("KELOMPOK ID " + id + " UPDATED",AppConstant.ResponseCode.SUCCESS, user, HttpStatus.OK);
            } catch (Exception e) {
                return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
            } 
        } 
            
    
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseUtil.build("USER ID " + id + " DELETED",AppConstant.ResponseCode.SUCCESS, null, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping(value = "/change-password")
    public ResponseEntity<Object> changePassword(Principal principal, @RequestBody UserDTO request) {
    try {
        request.setEmail(principal.getName());
        User user = userService.changePassword(request);
        return ResponseUtil.build("PASSWORD CHANGED",AppConstant.ResponseCode.SUCCESS, user, HttpStatus.OK);
    } catch (Exception e) {
        return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
}   