package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Session;
import com.example.demo.entity.User;
import com.example.demo.entity.Vaksin;
import com.example.demo.entity.dto.SessionDTO;
import com.example.demo.repository.SessionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VaksinRepository;
import com.example.demo.repository.SessionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SessionService {
    @Autowired
    private VaksinRepository vaksinRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public List<Session> getSession() {
        return sessionRepository.findAll();
    }

    public Optional<Session> getSessionById(Long id) {
        return sessionRepository.findById(id);
    }

    public Session save(SessionDTO request) {
        try{    
            Session session = new Session();
            log.info("search user id {}", request.getIdHealth());
            User user = userRepository.findById(request.getIdHealth())
                .orElseThrow(()->  new Exception( " Id User " + request.getIdHealth() + "Not Found"));
            Vaksin vaksin = vaksinRepository.searchForSession(request.getNama(), request.getIdHealth())
                .orElseThrow(()->  new Exception( " Vaksin " + request.getNama() + "by " + request.getIdHealth()+"Not Found"));
    
            log.info("save session");
            session.setUser(user);
            session.setVaksin(vaksin);
            session.setDate(request.getDate());
            session.setStart(request.getStart());
            session.setEnd(request.getEnd());;
            sessionRepository.save(session);
    
            return session;
        }
            catch(Exception e){
                log.error("save error" + e.getMessage());
                return e.getMessage();
            }
    }

    public Optional<Session> updateSession( Long id, SessionDTO request) {
        try{    
            Optional <Session> session = sessionRepository.findById(id);
            User user = userRepository.searchById(request.getIdHealth())
                .orElseThrow(()->  new Exception( " Id User" + request.getIdHealth() + "Not Found"));
            Vaksin vaksin = vaksinRepository.searchByName(request.getNama())
                .orElseThrow(()->  new Exception( " Nama Vaksin" + request.getNama() + "Not Found"));

        session.ifPresent(res -> {
            res.setUser(user);
            res.setVaksin(vaksin);
            res.setDate(request.getDate());
            res.setStart(request.getStart());
            res.setEnd(request.getEnd());
            sessionRepository.save(res);
        });
        return session;
    }
        catch(Exception e){
            return null;
        }
    }

    public String deleteSession(Long id) {
        Optional<Session> sessionById = sessionRepository.findById(id);
        sessionById.ifPresent(res -> {
            sessionRepository.delete(res);
        });
        if(sessionById.isPresent()){
            return "Delete Success";
        }
        else{
            return "Delete Failed";
        }
    }

}
