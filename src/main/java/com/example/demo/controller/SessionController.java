package com.example.demo.controller;

import java.sql.Date;
import java.util.List;

import com.example.demo.constant.AppConstant;
import com.example.demo.entity.Session;
import com.example.demo.entity.dto.SessionDTO;
import com.example.demo.service.SessionService;
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
@RequestMapping("/session")
public class SessionController {
    @Autowired
    private SessionService sessionService;
    
    @GetMapping(value = "")
    public ResponseEntity<?> getSession() {
        try {
            List<Session> sessions =  sessionService.getSession();
            return ResponseUtil.build("GET All SESSION",AppConstant.ResponseCode.SUCCESS, sessions, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getSession(@PathVariable(value = "id") Long id) {
        try {
            Session session =  sessionService.getSessionById(id);
            return ResponseUtil.build("GET SESSION DETAIL",AppConstant.ResponseCode.SUCCESS, session, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }
    @GetMapping(value = "/user/{idUser}")
    public ResponseEntity<?> findSessionByUserId(@PathVariable(value = "idUser") Long idUser) {
        try {
            List<Session> sessions =  sessionService.getByUserId(idUser);
            return ResponseUtil.build("GET SESSION BY USER ID ",AppConstant.ResponseCode.SUCCESS, sessions, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
            } 
    }
    @GetMapping(value = "/date/{date}")
    public ResponseEntity<?> findSessionByDate(@PathVariable(value = "date") Date date) {
        try {
            List<Session> sessions =  sessionService.getByDate(date);
            return ResponseUtil.build("GET SESSION BY USER DATE ",AppConstant.ResponseCode.SUCCESS, sessions, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
            }  
    }
    
    @GetMapping(value = "/date/{date}/{kota}")
    public ResponseEntity<?> findSessionByDateAndUser_kota(@PathVariable(value = "date") Date date, @PathVariable(value = "kota") String kota) {
        try {
            List<Session> sessions =  sessionService.getByDateAndUser_kota(date, kota);
            return ResponseUtil.build("GET SESSION BY USER DATE AND CITY",AppConstant.ResponseCode.SUCCESS, sessions, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
            }   
    }


    @PutMapping(value = "/{id}") 
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateSession(
        @PathVariable Long id, @RequestBody SessionDTO  request) {
            try {
                Session session = sessionService.updateSession(id, request);
                return ResponseUtil.build("SESSION ID " + id + " UPDATED ",AppConstant.ResponseCode.SUCCESS, session, HttpStatus.OK);
            } catch (Exception e) {
                return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
            } 
    } 

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createNewSession(@RequestBody SessionDTO request) {
        try {
            Session session = sessionService.save(request);
            return ResponseUtil.build("SESSION CREATED",AppConstant.ResponseCode.SUCCESS, session, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteSession(@PathVariable (value = "id") Long id) {
        try {
            sessionService.deleteSession(id);
            return ResponseUtil.build("SESSION ID " + id + " DELETED",AppConstant.ResponseCode.SUCCESS, null, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }
    
}
