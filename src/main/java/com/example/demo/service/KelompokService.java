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
    private UserRepository userRepository;

    public ResponseEntity<Object> getKelompok() {
        try {
            log.info("Get all kelompok");
            List<Kelompok> kelompok = kelompokRepository.findAll();
            if (kelompok.isEmpty()) {
                log.info("kelompok is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, kelompok, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by get all kelompok, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getByUserId( Long idUser) {
        try {
            log.info("Get kelompok by user parent's id");
            List<Kelompok> kelByParent = kelompokRepository.findByUser_idUser(idUser);
            if (kelByParent.isEmpty()) {
                log.info("kelompok is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, kelByParent, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error in get kelompok by parent user's id , Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getByIdAndHubungan(Long id, String hubungan) {
        try {
            log.info("Get kelompok by user id and hubungan");
            List<Kelompok> kelIdHub = kelompokRepository.findByUser_IdUserAndHubungan(id, hubungan);
            if (kelIdHub.isEmpty()) {
                log.info("kelompok is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, kelIdHub, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error in kelompok by user id and hubungan, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Object> findById(Long id) {
        try {
            log.info("Get kelompok by id");
            Optional<Kelompok> kelById = kelompokRepository.findById(id);
            if (kelById.isEmpty()) {
                log.info("kelompok is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, kelById, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error in get kelompok by id , Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    public ResponseEntity<Object> save(KelompokDTO request) {
    try{    
        log.info("save new kelompok: {}", request);
        log.info("search user id {}", request.getIdUser());
        
        Optional<User> user = userRepository.findById(request.getIdUser());
        if(user.isEmpty()) {
            log.info("parent user is empty");
            return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
        }
        Kelompok kelompok = new Kelompok(); 
        kelompok.setUser(user.get());
        kelompok.setNik(request.getNik());
        kelompok.setHubungan(request.getHubungan());
        kelompok.setNamaKelompok(request.getNamaKelompok());
        kelompok.setTlp(request.getTlp());
        kelompok.setTglLahir(request.getTglLahir()); 
        kelompok.setGender(request.getGender());
        kelompokRepository.save(kelompok);

        return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, kelompok, HttpStatus.OK);
    } catch (Exception e) {
        log.error("Get an error by executing create new kelompok, Error : {}",e.getMessage());
        return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
    
    public ResponseEntity<Object> updateKelompok(Long id, KelompokDTO  request) {
        try{    
            log.info("search kelompok: ");
            Optional<Kelompok> kelompok = kelompokRepository.findById(id);
            if (kelompok.isEmpty()) {
                log.info("kelompok not found");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            log.info("search kelompok parent's by id : "+request.getIdUser());
            Optional<User> user = userRepository.searchById(request.getIdUser());
            if(user.isEmpty()) {
                log.info("parent user kelompok's id: "+ request.getIdUser()+" not found");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }

            log.info("Update kelompok: {}", request);
        kelompok.ifPresent(res -> {
            res.setUser(user.get());
            res.setNik(request.getNik());
            res.setHubungan(request.getHubungan());
            res.setNamaKelompok(request.getNamaKelompok());
            res.setTlp(request.getTlp());
            res.setTglLahir(request.getTglLahir());
            res.setGender(request.getGender());
            kelompokRepository.save(res);
        });
         return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, kelompok, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by update kelompok, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
       
    }

    
    public ResponseEntity<Object> deleteKelompok(Long id) {
            try {
                log.info("Check by Kelompok id: "+id);
                Optional<Kelompok> kelompokById = kelompokRepository.findById(id);
                if(kelompokById.isEmpty()){
                    log.info("Kelompok id "+id+ "NOT FOUND");
                    return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
                }
                log.info("Executing delete kelompok by id: {}", id);
                kelompokById.ifPresent(res -> {
                    kelompokRepository.delete(res);
                });
            } catch (EmptyResultDataAccessException e) {
                log.error("Data not found. Error: {}", e.getMessage());
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, null, HttpStatus.OK); 
    }
    
}
