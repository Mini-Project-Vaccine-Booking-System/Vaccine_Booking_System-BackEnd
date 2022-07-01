package com.example.demo.controller;

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
    public List<Session> getSession() {
        return sessionService.getSession();
    }
    
    @GetMapping(value = "/{id}")
    public Optional <Session> getSession(@PathVariable(value = "id") Long id) {
        return sessionService.getSessionById(id);
    }
    @GetMapping(value = "/user/{idUser}")
    public List <Session> findSessionByUserId(@PathVariable(value = "idUser") Long idUser) {
        return sessionService.getByUserId(idUser);
    }

    @PostMapping("")
    public Session createNewSession(@RequestBody SessionDTO request) {
      return sessionService.save(request);
  }

    @DeleteMapping(value = "/{id}")
    public String deleteSession(@PathVariable (value = "id") Long id) {
      return sessionService.deleteSession(id);
    }
    @PutMapping(value = "/{id}") 
    public Optional<Session> updateSession(
        @PathVariable Long id, @RequestBody SessionDTO  request) {
            return sessionService.updateSession(id, request);
    }
    
}
