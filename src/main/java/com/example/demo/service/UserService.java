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
        log.info ("Save user: {}",request);
        // User user= User.builder()
        //     .email(request.getEmail().toLowerCase())
        //     .nik(request.getNik())
        //     .noHp(request.getNoHp())
        //     .nama(request.getNama())
        //     .gender(request.getGender())
        //     .image(request.getImage())
        //     .tglLahir(request.getTglLahir())
        //     .address(request.getAddress())
        //     .username(request.getUsername())
        //     .password(request.getPassword())
        //     .kota(request.getKota())
        //     .role(request.getRole())
        //     .updated_at(request.getUpdated_at())
        //     .created_at(request.getCreated_at());
        try {
            request.setEmail(request.getEmail().toLowerCase());
            User user = userRepository.save(request);
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
            Optional<User> userDetail = userRepository.findById(id);
            if (userDetail.isEmpty()) {
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
            if (userCity.isEmpty()) {
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
            if (userCitizen.isEmpty()) {
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
            if (userHealth.isEmpty()) {
                log.info("health facilities is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, userHealth, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by get all health facilities, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object>  updateCitizen( Long id,UserDTO  citizen) {
            // Optional<User> citizenById = userRepository.findById(id);
            try {
                log.info("Update user: {}", citizen);
                Optional<User> citizenById = userRepository.findById(id);
                if (citizenById.isEmpty()) {
                    log.info("user not found");
                    return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
                }
        
            citizenById.get().setNik(citizen.getNik());
            citizenById.get().setNoHp(citizen.getNoHp());
            citizenById.get().setNama(citizen.getNama());
            citizenById.get().setRole(citizen.getRole());
            citizenById.get().setGender(citizen.getGender());
            citizenById.get().setTglLahir(citizen.getTglLahir());
            citizenById.get().setAddress(citizen.getAddress());
            citizenById.get().setKota(citizen.getKota());
            citizenById.get().setImage(citizen.getImage());
            citizenById.get().setUsername(citizen.getUsername());
            citizenById.get().setPassword(citizen.getPassword());
            citizenById.get().setUpdatedAt(citizen.getUpdated_at());
            userRepository.save(citizenById.get());
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, citizenById.get(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by update course, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Object> deleteCitizen( Long id) {
        
        try {
            log.info("Executing delete user by id: {}", id);
            Optional<User> citizenById = userRepository.findById(id);
            citizenById.ifPresent(res -> {
                userRepository.delete(res);
            });
        } catch (EmptyResultDataAccessException e) {
            log.error("Data not found. Error: {}", e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
        }
        return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, null, HttpStatus.OK);       
    }

}
