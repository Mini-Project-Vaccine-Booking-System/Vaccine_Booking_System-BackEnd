package com.example.demo.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.util.*;
import com.example.demo.constant.*;
import com.example.demo.entity.base.*;
import org.springframework.http.*;
import org.springframework.dao.*;

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
    private UserService userService;
    @Autowired
    private VaksinService vaksinService;

    @Autowired
    private VaksinRepository vaksinRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public List<Session> getSession() {
        try {
            log.info("Get all session");
            List<Session> session = sessionRepository.findAll();
            if (session.isEmpty()) {
                log.info("session is empty");
                throw new Exception("SESSION IS EMPTY");

            }
            return session;

        } catch (Exception e) {
            log.error("Get an error by get all session, Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e);

        }
    }

    public Session getSessionById(Long id) {
        try {
            log.info("Get session by id");
            Session sessionById = sessionRepository.findById(id)
            .orElseThrow(() -> new Exception("SESSION ID " + id + " NOT FOUND"));
            return sessionById;
                    
        } catch (Exception e) {
            log.error("Get an error in get session by id , Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e);

        }
    }

    public List<Session> getByUserId( Long idUser) {
        try {
            log.info("Get session by health parent's id");
            List<Session> sessionByParent = sessionRepository.findByUser_idUser(idUser);
            if (sessionByParent.isEmpty()) {
                log.info("session is empty");
                throw new Exception("SESSION BY USER ID "+ idUser+" IS EMPTY"); 

            }
            return sessionByParent;

        } catch (Exception e) {
            log.error("Get an error in get session by parent health's id , Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e);

        }
    }

    public List<Session> getByDate (Date date) {
        try {
            log.info("Get session by date: "+date);
            List<Session> sessionByDate = sessionRepository.findByDate(date);
            if (sessionByDate.isEmpty()) {
                log.info("session is empty");
                throw new Exception("SESSION BY DATE "+date+" IS EMPTY"); 

            }
            return sessionByDate;

        } catch (Exception e) {
            log.error("Get an error in get session by date , Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e); 

        }

    }

    public List<Session> getByDateAndUser_kota(Date date, String kota) {
        try {
            log.info("Get session by date and user kota");
            List<Session> sesDateKota = sessionRepository.findByDateAndUser_kota(date, kota);
            if (sesDateKota.isEmpty()) {
                log.info("session is empty");
                throw new Exception("SESSION BY DATE "+date+ " IN " +kota+ " IS EMPTY");

            }
            return sesDateKota;

        } catch (Exception e) {
            log.error("Get an error in session by user city and date, Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e); 

        }
    }


    public Session save(SessionDTO request) {
        try{    
            log.info("save new session: {}", request);
            
            log.info("search user id {}", request.getIdHealth());
            User user = userService.findById(request.getIdHealth());
            
            log.info("search vaksin name: ", request.getNama(), " vaksin health id: ", request.getIdHealth());
            Optional <Vaksin> vaksin = vaksinRepository.searchForSession(request.getNama(), request.getIdHealth());
            if(vaksin.isEmpty()) {
                log.info(" Vaksin " + request.getNama() + "by " + request.getIdHealth()+"Not Found");
                throw new Exception("VAKSIN "+request.getNama()+ " BY " +request.getIdHealth()+ " IS EMPTY");

            }            
            Session session = new Session();
            log.info("save session");
            session.setUser(user);
            session.setVaksin(vaksin.get());
            session.setDate(request.getDate());
            session.setStart(request.getStart());
            session.setEnd(request.getEnd());;
            session.setStok(request.getStok());
            sessionRepository.save(session);
            return session;
    

        }catch (Exception e) {
            log.error("Get an error by executing create new session, Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public Session updateSession( Long id, SessionDTO request) {
        try{    
            log.info("search session: ");
            Session session = sessionRepository.findById(id)
            .orElseThrow(() -> new Exception("SESSION ID " + id +" NOT FOUND"));
            
            
            log.info("search user id {}", request.getIdHealth());
            User user = userService.findById(request.getIdHealth());
           
            
            log.info("search vaksin name: ", request.getNama(), " vaksin health id: ", request.getIdHealth());
            Optional <Vaksin> vaksin = vaksinRepository.searchForSession(request.getNama(), request.getIdHealth());
            if(vaksin.isEmpty()) {
                log.info(" Vaksin " + request.getNama() + "by " + request.getIdHealth()+"Not Found");
                throw new Exception("VAKSIN "+request.getNama()+ " BY " +request.getIdHealth()+ " IS EMPTY");

            }  
            log.info("Update session: {}", request);
        
            session.setUser(user);
            session.setVaksin(vaksin.get());
            session.setDate(request.getDate());
            session.setStart(request.getStart());
            session.setEnd(request.getEnd());
            session.setStok(request.getStok());
            sessionRepository.save(session);
        
            return session;

        }catch (Exception e) {
        log.error("Get an error by update kelompok, Error : {}",e.getMessage());
        throw new RuntimeException(e.getMessage(), e);

    }
    }

    public void  deleteSession(Long id) {
        try {
            log.info("Check by Session id: "+id);
            Session session = sessionRepository.findById(id)
            .orElseThrow(() -> new Exception("SESSION ID " + id +" NOT FOUND"));
                       
            log.info("Executing delete session by id: {}", id);
                sessionRepository.deleteById(id);

        } catch (Exception e) {
            log.error("Data not found. Error: {}", e.getMessage());
            throw new RuntimeException(e.getMessage(), e);

        }

    }


}