package com.example.demo.service;

import java.util.List;
import java.util.Optional;


import com.example.demo.entity.Kelompok;
import com.example.demo.entity.User;
import com.example.demo.entity.dto.KelompokDTO;
import com.example.demo.repository.KelompokRepository;
import com.example.demo.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KelompokService {
    @Autowired
    private KelompokRepository kelompokRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Kelompok> getKelompok() {
        return kelompokRepository.findAll();
    }

    public Kelompok save(KelompokDTO request) {
    try{    
        Kelompok kelompok = new Kelompok();
        log.info("search user id {}", request.getIdUser());
        User user = userRepository.findById(request.getIdUser())
            .orElseThrow(()->  new Exception( " Id User" + request.getIdUser() + "Not Found"));

        log.info("save kelompok");
        kelompok.setUser(user);
        kelompok.setNik(request.getNik());
        kelompok.setHubungan(request.getHubungan());
        kelompok.setNamaKelompok(request.getNamaKelompok());
        kelompok.setTlp(request.getTlp());
        kelompok.setTglLahir(request.getTglLahir());
        kelompokRepository.save(kelompok);

        return kelompok;
    }
        catch(Exception e){
            log.error("save error");
            return null;
        }
    }
    public Optional <Kelompok> findById(Long id) {
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
            res.setHubungan(request.getHubungan());
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
