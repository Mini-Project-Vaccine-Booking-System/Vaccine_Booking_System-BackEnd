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

import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vaksin")
public class VaksinController {
    @Autowired
    private VaksinService vaksinService;

    @GetMapping(value = "")
    public ResponseEntity<Object> getVaksin() {
        return vaksinService.getvaksin();
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getVaksin(@PathVariable(value = "id") Long id) {
        return vaksinService.findById(id);
    }
    @GetMapping(value = "/user/{idUser}")
    public ResponseEntity<Object> findVaksinByUserId(@PathVariable(value = "idUser") Long idUser) {
        return vaksinService.getByUserId(idUser);
    }
    
    @PostMapping(value = "")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createNewVaksin(@RequestBody VaksinDTO request) {
        return vaksinService.save(request);
    }
    
    @PutMapping(value = "/{id}") 
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> updateVaksin(
        @PathVariable Long id, @RequestBody VaksinDTO  request) {
            return vaksinService.updateVaksin(id, request);
    }
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteVaksin(@PathVariable (value = "id") Long id) {
      return vaksinService.deleteVaksin(id);
    }

    }

