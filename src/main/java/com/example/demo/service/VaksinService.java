package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.User;
import com.example.demo.entity.Vaksin;
import com.example.demo.entity.dto.VaksinDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VaksinRepository;
import com.example.demo.util.*;
import com.example.demo.constant.*;
import com.example.demo.entity.base.*;
import org.springframework.http.*;
import org.springframework.dao.*;

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


    public ResponseEntity<Object> getvaksin() {
        try {
            log.info("Get all vaksin");
            List<Vaksin> vaksin = vaksinRepository.findAll();
            if (vaksin.isEmpty()) {
                log.info("vaksin is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, vaksin, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by get all vaksin, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> findById( Long id) {
        try {
            log.info("Get vaksin by id");
            Optional<Vaksin> vaksinById = vaksinRepository.findById(id);
            if (vaksinById.isEmpty()) {
                log.info("vaksin is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, vaksinById, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error in get vaksin by id , Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getByUserId( Long idUser) {
        try {
            log.info("Get vaksin by user parent's id");
            List<Vaksin> vaksinByParent = vaksinRepository.findByUser_idUser(idUser);
            if (vaksinByParent.isEmpty()) {
                log.info("vaksin is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, vaksinByParent, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error in get vaksin by parent user's id , Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> save(VaksinDTO request) {
        try{    
            log.info("save new vaksin: {}", request);
            log.info("search user id {}", request.getIdHealth());
            Optional<User> user = userRepository.findById(request.getIdHealth());
            if(user.isEmpty()) {
            log.info("parent user is empty");
            return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
        }
            Vaksin vaksin = new Vaksin();
            log.info("save vaksin");
            vaksin.setUser(user.get());
            vaksin.setNama(request.getNama());
            vaksin.setQuantity(request.getQuantity());
            vaksinRepository.save(vaksin);
    
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, vaksin, HttpStatus.OK);
        }catch (Exception e) {
            log.error("Get an error by executing create new vaksin, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateVaksin( Long id, VaksinDTO request) {
        try{    
            log.info("search vaksin: ");
            Optional<Vaksin>vaksin= vaksinRepository.findById(id);
            if (vaksin.isEmpty()) {
                log.info("vaksin not found");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            log.info("search vaksin parent's by id : "+request.getIdHealth());
            Optional<User> user = userRepository.searchById(request.getIdHealth());
            if(user.isEmpty()) {
                log.info("parent user kelompok's id: "+ request.getIdHealth()+" not found");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }

            log.info("Update vaksin: {}", request);
        vaksin.ifPresent(res -> {
            res.setUser(user.get());
            res.setNama(request.getNama());
            res.setQuantity(request.getQuantity());
            vaksinRepository.save(res);
        });
        return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, vaksin, HttpStatus.OK);
    }catch (Exception e) {
        log.error("Get an error by update kelompok, Error : {}",e.getMessage());
        return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    public ResponseEntity<Object> deleteVaksin(Long id) {
        try {
            log.info("Check by vaksin id: "+id);
            Optional<Vaksin> vaksinById = vaksinRepository.findById(id);
            if(vaksinById.isEmpty()){
                log.info("Vaksin id "+id+ " NOT FOUND");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            log.info("Executing delete vaksin by id: {}", id);
            vaksinById.ifPresent(res -> {
                vaksinRepository.delete(res);
            });
        } catch (EmptyResultDataAccessException e) {
            log.error("Data not found. Error: {}", e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
        }
        return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, null, HttpStatus.OK); 

    }
}

