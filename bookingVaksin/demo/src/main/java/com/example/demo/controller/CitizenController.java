package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Citizen;
import com.example.demo.entity.dto.CitizenDTO;
import com.example.demo.repository.CitizenRepository;

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
public class CitizenController {
    @Autowired
    private CitizenRepository citizenRepository;

    @GetMapping("/user")
    public List<Citizen> getCitizens() {
        return citizenRepository.findAll();
    }
    @GetMapping("/user/{id}")
    public Optional <Citizen> geCitizens(@PathVariable Long id) {
        return citizenRepository.findById(id);
    }
    @PostMapping("/user")
    public Citizen createNewCitizen(@RequestBody Citizen payload) {
        return citizenRepository.save(payload);
    }
    @PutMapping("/user/{id}") 
    public Optional<Citizen> updateCitizen(
        @PathVariable Long id, 
        @RequestBody Citizen  citizen) {
            Optional<Citizen> citizenById = citizenRepository.findById(id);
        
        citizenById.ifPresent(res -> {
            res.setKk(citizen.getKk());
            res.setNo_hp(citizen.getNo_hp());
            res.setNama_user(citizen.getNama_user());
            res.setGender(citizen.getGender());
            res.setTgl_lahir(citizen.getTgl_lahir());
            res.setAddress(citizen.getAddress());
            res.setUsername(citizen.getUsername());
            res.setRole(citizen.getRole());
            res.setUpdated_at(citizen.getUpdated_at());
            citizenRepository.save(res);
        });
        return citizenById;
    }
    @DeleteMapping("/citizens/{id}")
    public void deleteCitizen(@PathVariable Long id) {
        Optional<Citizen> citizenById = citizenRepository.findById(id);
        citizenById.ifPresent(res -> {
            citizenRepository.delete(res);
        });
    }
    @GetMapping("/citizenDTO")
    public List<CitizenDTO> getCitizenDTO() {
        List<Citizen> citizen = citizenRepository.findAll();
        List<CitizenDTO> citizenDTO = new ArrayList<>();
        citizen.forEach(res -> {
            CitizenDTO citizen1 = new CitizenDTO();
            citizen1.setId_user(res.getId_user());
            citizen1.setKk(res.getKk());
            citizen1.setNo_hp(res.getNo_hp());
            citizen1.setNama_user(res.getNama_user());
            citizen1.setGender(res.getGender());
            citizen1.setTgl_lahir(res.getTgl_lahir());
            citizen1.setAddress(res.getAddress());
            citizen1.setUsername(res.getUsername());
            citizen1.setRole(res.getRole());
            citizen1.setUpdated_at(res.getUpdated_at());
            citizen1.setCreated_at(res.getCreated_at());
            citizenDTO.add(citizen1);
        });
        return citizenDTO;
    }

    
}
