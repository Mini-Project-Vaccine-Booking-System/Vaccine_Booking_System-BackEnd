package com.example.demo.controller;

import java.util.List;

import com.example.demo.constant.AppConstant;
import com.example.demo.entity.Kelompok;
import com.example.demo.entity.dto.KelompokDTO;
import com.example.demo.service.KelompokService;
import com.example.demo.util.ResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kelompok")
public class KelompokController {
    @Autowired
    private KelompokService kelompokService;


    @GetMapping(value = "")
    public ResponseEntity<?> getKelompok() {
        try {
            List<Kelompok> kelompoks =  kelompokService.getKelompok();
            return ResponseUtil.build("GET All KELOMPOK",AppConstant.ResponseCode.SUCCESS, kelompoks, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getKelompok(@PathVariable(value = "id") Long id) {
        try {
            Kelompok kelompok =  kelompokService.findById(id);
            return ResponseUtil.build("GET KELOMPOK DETAIL",AppConstant.ResponseCode.SUCCESS, kelompok, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(), AppConstant.ResponseCode.UNKNOWN_ERROR,null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/user/{idUser}")
    public ResponseEntity<?> findKelompokByUserId(@PathVariable(value = "idUser") Long idUser) {
    try {
        List<Kelompok> kelompoks =  kelompokService.getByUserId(idUser);
        return ResponseUtil.build("GET KELOMPOK BY USER ID ",AppConstant.ResponseCode.SUCCESS, kelompoks, HttpStatus.OK);
    } catch (Exception e) {
        return ResponseUtil.build(e.getMessage(), AppConstant.ResponseCode.UNKNOWN_ERROR,null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}/{hubungan}")
    public ResponseEntity<?> findKelompokByIdAndHubungan(@PathVariable(value = "id") Long id, @PathVariable(value = "hubungan") String hubungan) {
        try {
            List<Kelompok> kelompoks =  kelompokService.getByIdAndHubungan(id, hubungan);
            return ResponseUtil.build("GET KELOMPOK BY USER ID ",AppConstant.ResponseCode.SUCCESS, kelompoks, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
    
        }
    }

    @PostMapping(value = "")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createNewKelompok(@RequestBody KelompokDTO request) {
        try {
            Kelompok kelompok = kelompokService.save(request);
            return ResponseUtil.build("KELOMPOK CREATED",AppConstant.ResponseCode.SUCCESS, kelompok, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}") 
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateKelompok(
        @PathVariable Long id, @RequestBody KelompokDTO  request) {
            try {
                Kelompok kelompok = kelompokService.updateKelompok(id, request);
                return ResponseUtil.build("KELOMPOK ID " + id + " UPDATED",AppConstant.ResponseCode.SUCCESS, kelompok, HttpStatus.OK);
            } catch (Exception e) {
                return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteKelompok(@PathVariable (value = "id") Long id) {
        try {
            kelompokService.deleteKelompok(id);
            return ResponseUtil.build("KELOMPOK ID " + id + " DELETED",AppConstant.ResponseCode.SUCCESS, null, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
       

    

}

