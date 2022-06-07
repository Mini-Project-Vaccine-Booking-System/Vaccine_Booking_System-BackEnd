package com.example.demo.controller;

import com.example.demo.entity.StokVaksin;
import com.example.demo.entity.dto.StokVaksinDTO;
import com.example.demo.payload.repository.StokVaksinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StokVaksinController {
    @Autowired
    private StokVaksinRepository stokVaksinRepository;

    @GetMapping("/stokVaksin")
    public List<StokVaksin> getStokVaksin() {
        return stokVaksinRepository.findAll();
    }
    @GetMapping("/stokVaksin/{id}")
    public Optional<StokVaksin> getStokVaksin(@PathVariable Long id) {
        return stokVaksinRepository.findById(id);
    }
    @PostMapping("/stokVaksin")
    public StokVaksin createNewstockVaksin(@RequestBody StokVaksin payload) {
        return stokVaksinRepository.save(payload);
    }
    @PutMapping("/stokVaksin/{id}")
    public Optional<StokVaksin> updateDenda(
            @PathVariable Long id,
            @RequestBody StokVaksin stokVaksin) {
        Optional<StokVaksin>stokVaksinById = stokVaksinRepository.findById(id);

        stokVaksinById.ifPresent(res -> {
            res.setHealth_id(stokVaksin.getHealth_id());
            res.setQuantity(stokVaksin.getQuantity());
            res.setJenis_vaksin(stokVaksin.getJenis_vaksin());
            res.setUpdate_at(stokVaksin.getUpdate_at());
            res.setCreated_ad(stokVaksin.getCreated_ad());
            res.setCreated_by(stokVaksin.getCreated_by());
            stokVaksinRepository.save(res);
        });
        return stokVaksinById;
    }
    @DeleteMapping("/stock/{id}")
    public void deleteStokVaksain(@PathVariable Long id) {
        Optional<StokVaksin> stokVaksinById = stokVaksinRepository.findById(id);
        stokVaksinById.ifPresent(res -> {
            stokVaksinRepository.delete(res);
        });
    }
    @GetMapping("/stokVaksinDTO")
        public List<StokVaksinDTO> getPeminjamanDTO() {
            List<StokVaksin> stokVaksins = stokVaksinRepository.findAll();
            List<StokVaksinDTO> stokVaksinDTO = new ArrayList<>();
            stokVaksins.forEach(res -> {
                StokVaksinDTO stokVaksin1 = new StokVaksinDTO();
                stokVaksin1.setId_stock(res.getId_stock());
                stokVaksin1.setQuantity(res.getQuantity());
                stokVaksin1.setJenis_vaksin(res.getJenis_vaksin());
                stokVaksin1.setUpdate_at(res.getUpdate_at());
                stokVaksin1.setCreated_ad(res.getCreated_ad());
                stokVaksin1.setCreated_by(res.getCreated_by());
                stokVaksinDTO.add(stokVaksin1);
            });
            return stokVaksinDTO;
        }
    }

