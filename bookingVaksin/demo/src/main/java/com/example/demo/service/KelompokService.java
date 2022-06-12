package com.example.demo.service;

import java.util.List;
import java.util.Optional;


import com.example.demo.entity.Kelompok;
import com.example.demo.entity.User;
import com.example.demo.entity.dto.KelompokDTO;
import com.example.demo.repository.KelompokRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KelompokService {
    @Autowired
    private KelompokRepository kelompokRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Kelompok> getKelompok() {
        return kelompokRepository.findAll();
    }

    public Kelompok save(Kelompok request) {
        return kelompokRepository.save(request);
    }
    public Optional <Kelompok> findById( Long id) {
        return kelompokRepository.findById(id);
    }
    
    public Optional<Kelompok> updateKelompok(Long id, KelompokDTO  request) {
        try{    
            Optional<Kelompok> kelompok = kelompokRepository.findById(id);
            User user = userRepository.searchById(request.getIdUser())
            .orElseThrow(()->  new Exception( " Id User" + request.getIdUser() + "Not Found"));

        kelompok.ifPresent(res -> {
            res.setUser(user);
            res.setNik(request.getNik());
            res.setAddress(request.getAddress());
            res.setUsername(request.getUsername());
            res.setNamaKelompok(request.getNamaKelompok());
            res.setTlp(request.getTlp());
            res.setTglLahir(request.getTglLahir());
            kelompokRepository.save(res);
        });
        return kelompok;
    }
        catch(Exception e){
            return null;
        }
       
    }

    
    public String deleteKelompok(Long id) {
        Optional<Kelompok> kelompokById = kelompokRepository.findById(id);
        kelompokById.ifPresent(res -> {
            kelompokRepository.delete(res);
        });
        return "Deleted";
    }
    
}
