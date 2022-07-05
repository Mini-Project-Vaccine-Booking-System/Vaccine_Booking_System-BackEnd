package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.demo.entity.User;
import com.example.demo.entity.dto.UserDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.*;
import com.example.demo.constant.*;
import com.example.demo.entity.base.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Object> save( User request) {
        try {
            request.setEmail(request.getEmail().toLowerCase());
            user = userRepository.save(request);
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, user, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by executing create new user, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
        

    public ResponseEntity<Object> getAll() {
        try {
            log.info("Get all user");
            List<User> user = userRepository.findAll();
            if (user.isEmpty()) {
                log.info("user is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, user, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by get all user, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> findById( Long id) {
        try {
            log.info("Get user detail");
            List<User> userDetail = userRepository.findById(id);
            if (user.isEmpty()) {
                log.info("user is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, userDetail, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by get user detail, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> findByCity( String kota) {
        try {
            log.info("Get health detail by city");
            List<User> userCity = userRepository.searchByCity(kota);
            if (user.isEmpty()) {
                log.info("user is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, userCity, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by get user health by city, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Object> getCitizen( ) {
        try {
            log.info("Get all citizen");
            List<User> userCitizen = userRepository.findCitizen();
            if (user.isEmpty()) {
                log.info("citizen is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, userCitizen, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by get all citizen, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Object> getHealth() {
        try {
            log.info("Get all health facilities");
            List<User> userHealth = userRepository.findHealth();
            if (user.isEmpty()) {
                log.info("health facilities is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, userHealth, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by get all health facilities, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Optional<User> updateCitizen( Long id,UserDTO  citizen) {
            Optional<User> citizenById = userRepository.findById(id);
        
        citizenById.ifPresent(res -> {
            res.setNik(citizen.getNik());
            res.setNoHp(citizen.getNoHp());
            res.setNama(citizen.getNama());
            res.setRole(citizen.getRole());
            res.setGender(citizen.getGender());
            res.setTglLahir(citizen.getTglLahir());
            res.setAddress(citizen.getAddress());
            res.setKota(citizen.getKota());
            res.setImage(citizen.getImage());
            res.setUsername(citizen.getUsername());
            res.setPassword(citizen.getPassword());
            res.setUpdatedAt(citizen.getUpdated_at());
            userRepository.save(res);
        });
        return citizenById;
    }

    public String deleteCitizen( Long id) {
        
        Optional<User> citizenById = userRepository.findById(id);
        citizenById.ifPresent(res -> {
            userRepository.delete(res);
        });
        if(citizenById.isPresent()){
            return "success";
        }
        else{
            return "failed";
        }
        
    }

}
