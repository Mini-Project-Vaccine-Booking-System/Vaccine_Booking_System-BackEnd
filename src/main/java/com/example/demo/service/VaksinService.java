package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;
import com.example.demo.entity.Vaksin;
import com.example.demo.entity.dto.VaksinDTO;
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
    private UserService userService;


    public List<Vaksin> getvaksin() {
        try {
            log.info("Get all vaksin");
            List<Vaksin> vaksin = vaksinRepository.findAll();
            if (vaksin.isEmpty()) {
                log.info("vaksin is empty");
                throw new Exception("VAKSIN IS EMPTY");

            }
            return vaksin;

        } catch (Exception e) {
            log.error("Get an error by get all vaksin, Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e);

        }
    }

    public Vaksin findById( Long id) {
        try {
            log.info("Get vaksin by id");
            Vaksin vaksinById = vaksinRepository.findById(id)
            .orElseThrow(() -> new Exception("VAKSIN ID " + id + " NOT FOUND"));
            return vaksinById;
            
        } catch (Exception e) {
            log.error("Get an error in get vaksin by id , Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e);

        }
    }

    public List<Vaksin> getByUserId( Long idUser) {
        try {
            log.info("Get vaksin by user parent's id");
            List<Vaksin> vaksinByParent = vaksinRepository.findByUser_idUser(idUser);
            if (vaksinByParent.isEmpty()) {
                log.info("vaksin is empty");
                throw new Exception("VAKSIN BY USER ID "+idUser+" IS EMPTY"); 

            }
            return vaksinByParent;

        } catch (Exception e) {
            log.error("Get an error in get vaksin by parent user's id , Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e);

        }
    }

    public Vaksin save(VaksinDTO request) {
        try{    
            log.info("save new vaksin: {}", request);
            log.info("search user id {}", request.getIdHealth());
            User user = userService.searchHealthById(request.getIdHealth());
                
            Vaksin vaksin = new Vaksin();
            log.info("save vaksin");
            vaksin.setUser(user);
            vaksin.setNama(request.getNama());
            vaksin.setQuantity(request.getQuantity());
            vaksinRepository.save(vaksin);

            return vaksin;

        }catch (Exception e) {
            log.error("Get an error by executing create new vaksin, Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e);

        }
    }

    public Vaksin updateVaksin( Long id, VaksinDTO request) {
        try{    
            log.info("search vaksin: ");
            Vaksin vaksin= vaksinRepository.findById(id)
            .orElseThrow(() -> new Exception("VAKSIN ID " + id +" NOT FOUND"));
                        
            log.info("search vaksin parent's by id : "+request.getIdHealth());
            User user = userService.findById(request.getIdHealth());
                    

            log.info("Update vaksin: {}", request);
        
            vaksin.setUser(user);
            vaksin.setNama(request.getNama());
            vaksin.setQuantity(request.getQuantity());
            vaksinRepository.save(vaksin);
        
            return vaksin;
    }catch (Exception e) {
        log.error("Get an error by update kelompok, Error : {}",e.getMessage());
        throw new RuntimeException(e.getMessage(), e);
    }
    }

    public void deleteVaksin(Long id) {
        try {
            log.info("Check by vaksin id: "+id);
            vaksinRepository.findById(id)
            .orElseThrow(() -> new Exception("VAKSIN ID " + id + " NOT FOUND"));
                    
            log.info("Executing delete vaksin by id: {}", id);
            vaksinRepository.deleteById(id);
                    
        } catch (Exception e) {
            log.error("Data not found. Error: {}", e.getMessage());
            throw new RuntimeException(e.getMessage(), e);

        }

        
    }
}

