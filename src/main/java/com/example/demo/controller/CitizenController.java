package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.User;
import com.example.demo.entity.dto.UserDTO;
import com.example.demo.service.UserService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value= "/user")
public class CitizenController {
    @Autowired
    private UserService userService;


    @GetMapping(value = "")
    public ResponseEntity<Object> getCitizens() {
        return userService.getAll();
    }
    @GetMapping(value = "/citizen")
    public ResponseEntity<Object> getUser() {
        return userService.getCitizen();
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getCitizen(@PathVariable(value = "id") Long id) {
        return userService.findById(id);
    }
    @GetMapping(value = "/search/{kota}")
    public ResponseEntity<Object> getUserByCity(@PathVariable(value = "kota") String kota) {
        return userService.findByCity(kota);
    }
    @GetMapping(value = "/health")
    public ResponseEntity<Object> getHealth() {
        return userService.getHealth();
    }
    @PostMapping(value = "")
    public  ResponseEntity<Object> createNewCitizen(@RequestBody User request) {
        return userService.save(request);
    }
          

    @PutMapping(value = "/{id}") 
    public Optional<User> updateCitizen(
        @PathVariable (value = "id") Long id, @RequestBody UserDTO  citizen) {
            return userService.updateCitizen(id, citizen);
    }
    @DeleteMapping(value = "/{id}")
    public String deleteCitizen(@PathVariable (value = "id") Long id) {
      return userService.deleteCitizen(id);
    }

   

    
}
