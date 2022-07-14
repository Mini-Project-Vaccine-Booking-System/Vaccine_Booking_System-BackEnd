package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import com.example.demo.util.*;
import com.example.demo.constant.*;
import com.example.demo.entity.base.*;
import org.springframework.http.*;
import org.springframework.dao.*;

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
    private UserService userService;
    // @Autowired
    // private UserRepository userRepository;


    public List <Kelompok> getKelompok() {
        try {
            log.info("Get all kelompok");
            List<Kelompok> kelompok = kelompokRepository.findAll();
            if (kelompok.isEmpty()) {
                log.info("kelompok is empty");
                throw new Exception("KELOMPOK IS EMPTY");
                //return ResponseUtil.build(AppConstant.ResponseCode., null, HttpStatus.NOT_FOUND);
            }
            return kelompok;
            //return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, kelompok, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by get all kelompok, Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
            //return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public Kelompok findById(Long id) {
        try {
            log.info("Get kelompok by id");
            Kelompok kelById = kelompokRepository.findById(id).
            orElseThrow(() -> new Exception("KELOMPOK ID " + id + " NOT FOUND"));
            return kelById;
            // if (kelById.isEmpty()) {
            //     log.info("kelompok is empty");

            //    // return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            // }
            // return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, kelById, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error in get kelompok by id , Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
            // return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Kelompok> getByUserId( Long idUser) {
        try {
            log.info("Get kelompok by user parent's id");
            List<Kelompok> kelByParent = kelompokRepository.findByUser_idUser(idUser);
            if (kelByParent.isEmpty()) {
                log.info("kelompok is empty");
                throw new Exception("KELOMPOK BY USER ID "+ idUser+" IS EMPTY"); 
                //return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            return kelByParent;
            //return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, kelByParent, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error in get kelompok by parent user's id , Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
            //return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Kelompok> getByIdAndHubungan(Long id, String hubungan) {
        try {
            log.info("Get kelompok by user id and hubungan");
            List<Kelompok> kelIdHub = kelompokRepository.findByUser_IdUserAndHubungan(id, hubungan);
            if (kelIdHub.isEmpty()) {
                log.info("kelompok is empty");
                throw new Exception("KELOMPOK BY USER ID AND HUBUNGAN IS EMPTY"); 
                //return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            return kelIdHub;
            //return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, kelIdHub, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error in kelompok by user id and hubungan, Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e); 
            //return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Kelompok save(KelompokDTO request) {
    try{    
        log.info("save new kelompok: {}", request);
        log.info("search user id {}", request.getIdUser());
        User user = userService.findById(request.getIdUser());

        //Optional<User> user = userRepository.findById(request.getIdUser());
        Kelompok kelompok = new Kelompok(); 
        kelompok.setUser(user);
        kelompok.setNik(request.getNik());
        kelompok.setHubungan(request.getHubungan());
        kelompok.setNamaKelompok(request.getNamaKelompok());
        kelompok.setTlp(request.getTlp());
        kelompok.setTglLahir(request.getTglLahir()); 
        kelompok.setGender(request.getGender());
        kelompokRepository.save(kelompok);
        return kelompok;
        //return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, kelompok, HttpStatus.OK);
    } catch (Exception e) {
        log.error("Get an error by executing create new kelompok, Error : {}",e.getMessage());
        throw new RuntimeException(e.getMessage(), e);
        //return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
    
    public Kelompok updateKelompok(Long id, KelompokDTO  request) {
        try{    
            log.info("search kelompok: ");
            Kelompok kelompok = kelompokRepository.findById(id)
            .orElseThrow(() -> new Exception("KELOMPOK ID " + id +" NOT FOUND"));
            
            log.info("search kelompok parent's by id : "+request.getIdUser());
            User user = userService.findById(request.getIdUser());
            log.info("Update kelompok: {}", request);
        
            kelompok.setUser(user);
            kelompok.setNik(request.getNik());
            kelompok.setHubungan(request.getHubungan());
            kelompok.setNamaKelompok(request.getNamaKelompok());
            kelompok.setTlp(request.getTlp());
            kelompok.setTglLahir(request.getTglLahir());
            kelompok.setGender(request.getGender());
            kelompok.setUpdatedAt(request.getUpdatedAt());
            kelompokRepository.save(kelompok);

            return kelompok;
         //return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, kelompok, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by update kelompok, Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
            //return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
       
    }

    
    public void deleteKelompok(Long id) {
            try {
                log.info("Check by Kelompok id: "+id);

                kelompokRepository.findById(id)
                .orElseThrow(() -> new Exception("KELOMPOK ID " + id + " NOT FOUND"));
                
                log.info("Executing delete kelompok by id: {}", id);
                    kelompokRepository.deleteById(id);

            } catch (Exception e) {
                log.error("Data not found. Error: {}", e.getMessage());
                throw new RuntimeException(e.getMessage(), e);
               // return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            //return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, null, HttpStatus.OK); 
    }
    
}
