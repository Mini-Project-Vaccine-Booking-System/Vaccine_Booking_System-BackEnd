package com.example.demo.controller;

import com.example.demo.entity.Vaksin;
import com.example.demo.entity.dto.VaksinDTO;
import com.example.demo.repository.VaksinRepository;
import com.example.demo.service.VaksinService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vaksin")
public class VaksinController {
    @Autowired
    private VaksinService vaksinService;

    @GetMapping(value = "")
    public List<Vaksin> getVaksin() {
        return vaksinService.getvaksin();
    }
    
    @GetMapping(value = "/{id}")
    public Optional <Vaksin> getVaksin(@PathVariable(value = "id") Long id) {
        return vaksinService.findById(id);
    }
    
    @PostMapping(value = "")
    public Vaksin createNewVaksin(@RequestBody VaksinDTO request) {
        return vaksinService.save(request);
    }
    
    @PutMapping(value = "/{id}") 
    public Optional<Vaksin> updateVaksin(
        @PathVariable Long id, @RequestBody VaksinDTO  request) {
            return vaksinService.updateVaksin(id, request);
    }
    @DeleteMapping(value = "/{id}")
    public String deleteVaksin(@PathVariable (value = "id") Long id) {
      return vaksinService.deleteVaksin(id);
    }

    }

