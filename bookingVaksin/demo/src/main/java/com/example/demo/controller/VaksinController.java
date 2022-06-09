package com.example.demo.controller;

import com.example.demo.entity.Vaksin;
import com.example.demo.entity.dto.VaksinDTO;
import com.example.demo.repository.VaksinRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class VaksinController {
    @Autowired
    private VaksinRepository vaksinRepository;

    @GetMapping("/vaksin")
    public List<Vaksin> getvaksin() {
        return vaksinRepository.findAll();
    }
    @GetMapping("/vaksin/{id}")
    public Optional<Vaksin> getvaksin(@PathVariable Long id) {
        return vaksinRepository.findById(id);
    }
    @PutMapping("/vaksin/{id}")
    public Optional<Vaksin> updateVaksin(
            @PathVariable Long id,
            @RequestBody Vaksin vaksin) {
        Optional<Vaksin>vaksinById = vaksinRepository.findById(id);

        vaksinById.ifPresent(res -> {
            res.setIdHealth(vaksin.getIdHealth());
            res.setNama(vaksin.getNama());
            res.setQuantity(vaksin.getQuantity());
            res.setUpdate_at(vaksin.getUpdate_at());
            res.setCreated_ad(vaksin.getCreated_ad());
            res.setCreated_by(vaksin.getCreated_by());
            vaksinRepository.save(res);
        });
        return vaksinById;
    }
    @DeleteMapping("/stock/{id}")
    public void deleteStokVaksain(@PathVariable Long id) {
        Optional<Vaksin> vaksinById = vaksinRepository.findById(id);
        vaksinById.ifPresent(res -> {
            vaksinRepository.delete(res);
        });
    }
    @GetMapping("/vaksinDTO")
        public List<VaksinDTO> getPeminjamanDTO() {
            List<Vaksin> vaksins = vaksinRepository.findAll();
            List<VaksinDTO> vaksinDTO = new ArrayList<>();
            vaksins.forEach(res -> {
                VaksinDTO vaksin1 = new VaksinDTO();
                vaksin1.setIdHealth(res.getIdHealth());
                vaksin1.setNama(res.getNama());
                vaksin1.setQuantity(res.getQuantity());
                vaksin1.setUpdate_at(res.getUpdate_at());
                vaksin1.setCreated_ad(res.getCreated_ad());
                vaksin1.setCreated_by(res.getCreated_by());
                vaksinDTO.add(vaksin1);
            });
            return vaksinDTO;
        }
    }

