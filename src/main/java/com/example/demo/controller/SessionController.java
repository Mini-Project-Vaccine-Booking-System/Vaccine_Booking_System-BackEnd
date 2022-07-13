package com.example.demo.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Session;
import com.example.demo.entity.dto.SessionDTO;
import com.example.demo.repository.SessionRepository;
import com.example.demo.service.SessionService;


@RestController
@RequestMapping("/session")
public class SessionController {
    @Autowired
    private SessionService sessionService;
    
    @GetMapping(value = "")
    public ResponseEntity<Object> getSession() {
        return sessionService.getSession();
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getSession(@PathVariable(value = "id") Long id) {
        return sessionService.getSessionById(id);
    }
    @GetMapping(value = "/user/{idUser}")
    public ResponseEntity<Object> findSessionByUserId(@PathVariable(value = "idUser") Long idUser) {
        return sessionService.getByUserId(idUser);
    }
    @GetMapping(value = "/date/{date}")
    public ResponseEntity<Object> findSessionByDate(@PathVariable(value = "date") Date date) {
        return sessionService.getByDate(date);
    }
    @GetMapping(value = "/date/{date}/{kota}")
    public ResponseEntity<Object> findSessionByDateAndUser_kota(@PathVariable(value = "date") Date date, @PathVariable(value = "kota") String kota) {
        return sessionService.getByDateAndUser_kota(date, kota);
    }


    @PutMapping(value = "/{id}") 
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> updateSession(
        @PathVariable Long id, @RequestBody SessionDTO  request) {
            return sessionService.updateSession(id, request);
    } 

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createNewSession(@RequestBody SessionDTO request) {
      return sessionService.save(request);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteSession(@PathVariable (value = "id") Long id) {
      return sessionService.deleteSession(id);
    }
    
}
