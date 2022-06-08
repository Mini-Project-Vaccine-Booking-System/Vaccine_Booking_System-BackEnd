package com.example.demo.controller;

import com.example.demo.repository.HealthFacilityRepository;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.HealthFacility;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class HealthFacilityController {
    @Autowired
    private HealthFacilityRepository healthFacilityRepository;

    @GetMapping("/health")
    public List<HealthFacility> getHealth(){
        return healthFacilityRepository.findAll();
    }

    @GetMapping("/health/{id}")
    public Optional <HealthFacility> getHealthbyId(@PathVariable Long id) {
        return healthFacilityRepository.findById(id);
    }

    @PostMapping("/health")
    public HealthFacility createNewHealth(@RequestBody HealthFacility payload) {
        return healthFacilityRepository.save(payload);
    }

    @PutMapping("/health/{id}") 
    public Optional<HealthFacility> updateHealth(
        @PathVariable Long id, 
        @RequestBody HealthFacility  health) {
            Optional<HealthFacility> healthById = healthFacilityRepository.findById(id);
        
            healthById.ifPresent(res -> {
            res.setEmail(health.getEmail());
            res.setName(health.getName());
            res.setAddress(health.getAddress());
            res.setCity(health.getCity());
            res.setImage(health.getImage());
            res.setUpdated_at(health.getUpdated_at());
            healthFacilityRepository.save(res);
        });
        return healthById;
    }
    
    @DeleteMapping("/health/{id}")
    public void deleteHealth(@PathVariable Long id) {
        Optional<HealthFacility> healthById = healthFacilityRepository.findById(id);
        healthById.ifPresent(res -> {
            healthFacilityRepository.delete(res);
        });
    }
}
