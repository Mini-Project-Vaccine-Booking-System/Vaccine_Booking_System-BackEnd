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
import com.example.demo.entity.dto.KelompokDTO;
import com.example.demo.repository.KelompokRepository;
import com.example.demo.service.KelompokService;

@RestController
@RequestMapping("/kelompok")
public class KelompokController {
    @Autowired
    private KelompokService kelompokService;


    @GetMapping(value = "")
    public List<Kelompok> getKelompok() {
        return kelompokService.getKelompok();
    }
    @GetMapping(value = "/{id}")
    public Optional <Kelompok> getKelompok(@PathVariable(value = "id") Long id) {
        return kelompokService.findById(id);
    }
    @PostMapping(value = "")
    public Kelompok createNewKelompok(@RequestBody KelompokDTO request) {
         return kelompokService.save(request);
    }

    @GetMapping(value = "/user/{idUser}")
    public List <Kelompok> findKelompokByUserId(@PathVariable(value = "idUser") Long idUser) {
        return kelompokService.getByUserId(idUser);
    }
    @GetMapping(value = "/{id}/{hubungan}")
    public List <Kelompok> findKelompokByIdAndHubungan(@PathVariable(value = "id") Long id, @PathVariable(value = "hubungan") String hubungan) {
        return kelompokService.getByIdAndHubungan(id, hubungan);
    }

    @PutMapping(value = "/{id}") 
    public Optional<Kelompok> updateKelompok(
        @PathVariable Long id, @RequestBody KelompokDTO  request) {
            return kelompokService.updateKelompok(id, request);
    }
    @DeleteMapping(value = "/{id}")
    public String deleteKelompok(@PathVariable (value = "id") Long id) {
      return kelompokService.deleteKelompok(id);
    }

    

}

