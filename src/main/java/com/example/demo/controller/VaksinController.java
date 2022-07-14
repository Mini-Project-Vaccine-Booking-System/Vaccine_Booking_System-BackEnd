package com.example.demo.controller;

import com.example.demo.constant.AppConstant;
import com.example.demo.entity.Vaksin;
import com.example.demo.entity.dto.VaksinDTO;
import com.example.demo.repository.VaksinRepository;
import com.example.demo.service.VaksinService;
import com.example.demo.util.ResponseUtil;

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
    public ResponseEntity<?> getVaksin() {
        try {
            List<Vaksin> vaksins =  vaksinService.getvaksin();
            return ResponseUtil.build("GET All VAKSIN",AppConstant.ResponseCode.SUCCESS, vaksins, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getVaksin(@PathVariable(value = "id") Long id) {
        try {
            Vaksin vaksin =  vaksinService.findById(id);
            return ResponseUtil.build("GET VAKSIN DETAIL",AppConstant.ResponseCode.SUCCESS, vaksin, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }

    @GetMapping(value = "/user/{idUser}")
    public ResponseEntity<?> findVaksinByUserId(@PathVariable(value = "idUser") Long idUser) {
        try {
            List<Vaksin> vaksins =  vaksinService.getByUserId(idUser);
            return ResponseUtil.build("GET VAKSIN BY USER ID ",AppConstant.ResponseCode.SUCCESS, vaksins, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
            } 
    }
    
    @PostMapping(value = "")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createNewVaksin(@RequestBody VaksinDTO request) {
        try {
            Vaksin vaksin = vaksinService.save(request);
            return ResponseUtil.build("VAKSIN CREATED",AppConstant.ResponseCode.SUCCESS, vaksin, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }
    
    @PutMapping(value = "/{id}") 
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateVaksin(
        @PathVariable Long id, @RequestBody VaksinDTO  request) {
            try {
                Vaksin vaksin = vaksinService.updateVaksin(id, request);
                return ResponseUtil.build("VAKSIN ID " + id + " UPDATED",AppConstant.ResponseCode.SUCCESS, vaksin, HttpStatus.OK);
            } catch (Exception e) {
                return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
            } 
    }
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteVaksin(@PathVariable (value = "id") Long id) {
        try {
            vaksinService.deleteVaksin(id);
            return ResponseUtil.build("VAKSIN ID " + id + " DELETED",AppConstant.ResponseCode.SUCCESS, null, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }

    }

