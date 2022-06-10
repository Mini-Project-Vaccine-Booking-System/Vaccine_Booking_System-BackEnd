package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Session;
import com.example.demo.repository.SessionRepository;

@RestController
@RequestMapping("/api")
public class SessionController {
    @Autowired
    private SessionRepository sessionRepository;
    
    @GetMapping("/session")
    public List<Session> getSession() {
        return sessionRepository.findAll();
    }

    @GetMapping("/session/{id}")
    public Optional<Session> getSessionById(@PathVariable Long id) {
        return sessionRepository.findById(id);
    }
    @PostMapping("/session")
    public Session createNewKelompok(@RequestBody Session payload) {
        return sessionRepository.save(payload);
    }
    
    
}
