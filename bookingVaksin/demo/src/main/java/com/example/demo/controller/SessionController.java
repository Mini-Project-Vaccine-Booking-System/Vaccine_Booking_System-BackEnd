package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Session;
import com.example.demo.entity.dto.SessionDTO;
import com.example.demo.repository.SessionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SessionController {
    @Autowired
    private SessionRepository sessionRepository;
    
    @GetMapping("/session")
    public List<Session> getsession() {
        return sessionRepository.findAll();
    }

    @GetMapping("/session/{id}")
    public Optional<Session> getsessionById(@PathVariable Long id) {
        return sessionRepository.findById(id);
    }
    @PostMapping("/session")
    public Session createNewKelompok(@RequestBody Session payload) {
        return sessionRepository.save(payload);
    }
    @PutMapping("/session/{id}")
    public Optional<Session> updatesession(
            @PathVariable Long id,
            @RequestBody Session session) {
        Optional<Session>sessionById = sessionRepository.findById(id);

        sessionById.ifPresent(res -> {
            res.setIdHealth(session.get);
            res.setNama(session.getNama());
            res.setQuantity(session.getQuantity());
            res.setUpdate_at(session.getUpdate_at());
            res.setCreated_ad(session.getCreated_ad());
            res.setCreated_by(session.getCreated_by());
            sessionRepository.save(res);
        });
        return sessionById;
    }

    @GetMapping("/sessionDTO")
        public List<SessionDTO> getPeminjamanDTO() {
            List<Session> session = sessionRepository.findAll();
            List<SessionDTO> sessionDTO = new ArrayList<>();
            session.forEach(res -> {
                SessionDTO session1 = new SessionDTO();
                session1.setIdHealth(res.ge);
                session1.setNama(res.getNama());
                session1.setQuantity(res.getQuantity());
                session1.setUpdate_at(res.getUpdate_at());
                session1.setCreated_ad(res.getCreated_ad());
                session1.setCreated_by(res.getCreated_by());
                sessionDTO.add(session);
            });
            return sessionDTO;
        }

}
