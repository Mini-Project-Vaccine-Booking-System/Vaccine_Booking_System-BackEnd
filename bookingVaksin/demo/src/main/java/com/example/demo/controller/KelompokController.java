package com.example.demo.controller;

import java.util.ArrayList;
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

import com.example.demo.entity.Kelompok;
import com.example.demo.repository.KelompokRepository;

@RestController
@RequestMapping("/api")
public class KelompokController {
    @Autowired
    private KelompokRepository kelompokRepository;

    @GetMapping("/kelompok")
    public List<Kelompok> getKelompok() {
        return kelompokRepository.findAll();
    }
    @PostMapping("/kelompok")
    public Kelompok createNewKelompok(@RequestBody Kelompok payload) {
        return kelompokRepository.save(payload);
    }
    @PutMapping("/kelompok/{id}") 
    public Optional<Kelompok> updateKelompok(
        @PathVariable Long id, 
        @RequestBody Kelompok  kelompok) {
            Optional<Kelompok> kelompokById = kelompokRepository.findById(id);
        
        kelompokById.ifPresent(res -> {
            // res.setId_user(kelompok.getId_user());
            // res.setId_kelompok(kelompok.getId_kelompok());
            res.setNik(kelompok.getNik());
            res.setAddress(kelompok.getAddress());
            res.setUsername(kelompok.getUsername());
            res.setNama_kelompok(kelompok.getNama_kelompok());
            res.setTlp(kelompok.getTlp());
            res.setTgl_lahir(kelompok.getTgl_lahir());
            kelompokRepository.save(res);
        });
        return kelompokById;
    }

    @DeleteMapping("/kelompok/{id}")
    public void deleteKelompok(@PathVariable Long id) {
        Optional<Kelompok> kelompokById = kelompokRepository.findById(id);
        kelompokById.ifPresent(res -> {
            kelompokRepository.delete(res);
        });
    }
    

}

