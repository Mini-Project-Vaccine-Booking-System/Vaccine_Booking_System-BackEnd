package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @PostMapping("/health")
    public Health createNewKelompok(@RequestBody Health payload) {
        return healthRepository.save(payload);
    }
    @GetMapping("/health/name")
    public List<Health> getHealthsByName(String name) {
        return healthRepository.findByName(name);
    }
    @GetMapping("/health/{id}")
    public Optional <Health> getHealths(@PathVariable Long id) {
        return healthRepository.findById(id);
    }
    @DeleteMapping("/health/{id}")
    public void deleteHealth(@PathVariable Long id) {
        Optional<Health> healthById = healthRepository.findById(id);
        healthById.ifPresent(res -> {
            healthRepository.delete(res);
        });
    }
    @PutMapping("/health/{id}")
    public Optional<Health> updateHealth(
        @PathVariable Long id, 
        @RequestBody Health  health) {
            Optional<Health> healthById = healthRepository.findById(id);
        
        healthById.ifPresent(res -> {
            // res.setId_user(health.getId_user());
            // res.setId_kelompok(health.getId_kelompok());
            res.setName(health.getName());
            res.setEmail(health.getEmail());
            res.setPassword(health.getPassword());
            res.setAddress(health.getAddress());
            res.setCity(health.getCity());
            res.setImage(health.getImage());
            healthRepository.save(res);
        });
        return healthById;
    }
    @GetMapping("/health/city")
    public List<Health> getHealthsByCity(String city) {
        return healthRepository.findByCity(city);
    }
}
