package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Health;
import com.example.demo.repository.HealthRepository;

@RestController
@RequestMapping("/api")
public class HealthController {
    @Autowired
    private HealthRepository healthRepository;

    @GetMapping("/health")
    public List<Health> getHealths() {
        return healthRepository.findAll();
    }
    @PostMapping
    public Health createNewKelompok(@RequestBody Health payload) {
        return healthRepository.save(payload);
    }
    @GetMapping("/health/name")
    public List<Health> getHealthsByName(String name) {
        return healthRepository.findByName(name);
    }
}
