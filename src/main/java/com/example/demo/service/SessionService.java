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
    private VaksinRepository vaksinRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public ResponseEntity<Object> getSession() {
        try {
            log.info("Get all session");
            List<Session> session = sessionRepository.findAll();
            if (session.isEmpty()) {
                log.info("session is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, session, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by get all session, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getSessionById(Long id) {
        try {
            log.info("Get session by id");
            Optional<Session> sessionById = sessionRepository.findById(id);
            if (sessionById.isEmpty()) {
                log.info("session is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, sessionById, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error in get session by id , Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getByUserId( Long idUser) {
        try {
            log.info("Get session by health parent's id");
            List<Session> sessionByParent = sessionRepository.findByUser_idUser(idUser);
            if (sessionByParent.isEmpty()) {
                log.info("session is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, sessionByParent, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error in get session by parent health's id , Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getByDate (Date date) {
        try {
            log.info("Get session by date: "+date);
            List<Session> sessionByDate = sessionRepository.findByDate(date);
            if (sessionByDate.isEmpty()) {
                log.info("session is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, sessionByDate, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error in get session by date , Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<Object> getByDateAndUser_kota(Date date, String kota) {
        try {
            log.info("Get session by date and user kota");
            List<Session> sesDateKota = sessionRepository.findByDateAndUser_kota(date, kota);
            if (sesDateKota.isEmpty()) {
                log.info("session is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, sesDateKota, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error in session by user city and date, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Object> save(SessionDTO request) {
        try{    
            log.info("save new session: {}", request);
            
            log.info("search user id {}", request.getIdHealth());
            Optional<User> user = userRepository.findById(request.getIdHealth());
            if(user.isEmpty()) {
                log.info("parent user is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            log.info("search vaksin name: ", request.getNama(), " vaksin health id: ", request.getIdHealth());
            Optional <Vaksin> vaksin = vaksinRepository.searchForSession(request.getNama(), request.getIdHealth());
            if(vaksin.isEmpty()) {
                log.info(" Vaksin " + request.getNama() + "by " + request.getIdHealth()+"Not Found");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }            
            Session session = new Session();
            log.info("save session");
            session.setUser(user.get());
            session.setVaksin(vaksin.get());
            session.setDate(request.getDate());
            session.setStart(request.getStart());
            session.setEnd(request.getEnd());;
            session.setStok(request.getStok());
            sessionRepository.save(session);
    
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, session, HttpStatus.OK);
        }catch (Exception e) {
            log.error("Get an error by executing create new session, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateSession( Long id, SessionDTO request) {
        try{    
            log.info("search session: ");
            Optional <Session> session = sessionRepository.findById(id);
            if (session.isEmpty()) {
                log.info("session not found");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            log.info("search user id {}", request.getIdHealth());
            Optional<User> user = userRepository.findById(request.getIdHealth());
            if(user.isEmpty()) {
                log.info("parent user is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            log.info("search vaksin name: ", request.getNama(), " vaksin health id: ", request.getIdHealth());
            Optional <Vaksin> vaksin = vaksinRepository.searchForSession(request.getNama(), request.getIdHealth());
            if(user.isEmpty()) {
                log.info(" Vaksin " + request.getNama() + "by " + request.getIdHealth()+"Not Found");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }  
            log.info("Update session: {}", request);
        session.ifPresent(res -> {
            res.setUser(user.get());
            res.setVaksin(vaksin.get());
            res.setDate(request.getDate());
            res.setStart(request.getStart());
            res.setEnd(request.getEnd());
            res.setStok(request.getStok());
            sessionRepository.save(res);
        });
        return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, session, HttpStatus.OK);
    }catch (Exception e) {
        log.error("Get an error by update kelompok, Error : {}",e.getMessage());
        return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    public ResponseEntity<Object> deleteSession(Long id) {
        try {
            log.info("Check by Session id: "+id);
            Optional<Session> sessionById = sessionRepository.findById(id);
            if(sessionById.isEmpty()){
                log.info("Session id "+id+ " NOT FOUND");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            log.info("Executing delete session by id: {}", id);
            sessionById.ifPresent(res -> {
                sessionRepository.delete(res);
            });
        } catch (EmptyResultDataAccessException e) {
            log.error("Data not found. Error: {}", e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
        }
        return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, null, HttpStatus.OK); 
    }

}
