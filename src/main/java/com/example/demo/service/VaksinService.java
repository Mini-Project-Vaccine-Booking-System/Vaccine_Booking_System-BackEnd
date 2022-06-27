package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.User;
import com.example.demo.entity.Vaksin;
import com.example.demo.entity.dto.VaksinDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VaksinRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VaksinService {
    @Autowired
    private VaksinRepository vaksinRepository;

    @Autowired
    private UserRepository userRepository;


    public List<Vaksin> getvaksin() {
        return vaksinRepository.findAll();
    }

    public Optional<Vaksin> findById( Long id) {
        return vaksinRepository.findById(id);
    }

    public Vaksin save(VaksinDTO request) {
        try{    
            Vaksin vaksin = new Vaksin();
            log.info("search user id {}", request.getIdHealth());
            User user = userRepository.findById(request.getIdHealth())
                .orElseThrow(()->  new Exception( " Id User" + request.getIdHealth() + "Not Found"));
    
            log.info("save vaksin");
            vaksin.setUser(user);
            vaksin.setNama(request.getNama());
            vaksin.setQuantity(request.getQuantity());
            vaksinRepository.save(vaksin);
    
            return vaksin;
        }
            catch(Exception e){
                log.error("save error");
                return null;
            }
    }

    public Optional<Vaksin> updateVaksin( Long id, VaksinDTO request) {
        try{    
            Optional<Vaksin>vaksin= vaksinRepository.findById(id);
            User user = userRepository.searchById(request.getIdHealth())
            .orElseThrow(()->  new Exception( " Id User" + request.getIdHealth() + "Not Found"));

        vaksin.ifPresent(res -> {
            res.setUser(user);
            res.setNama(request.getNama());
            res.setQuantity(request.getQuantity());
            vaksinRepository.save(res);
        });
        return vaksin;
    }
        catch(Exception e){
            return null;
        }
    }

    public String deleteVaksin(Long id) {
        Optional<Vaksin> vaksinById = vaksinRepository.findById(id);
        vaksinById.ifPresent(res -> {
            vaksinRepository.delete(res);
        });
        if (vaksinById.isPresent()) {
            return "Success";
        } else {
            return "Failed";
        }
    }
}

