package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.demo.entity.User;
import com.example.demo.entity.dto.UserDTO;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public User save( User request) {
        request.setEmail(request.getEmail().toLowerCase());
        return userRepository.save(request);
        
    }

    public ResponseEntity<Object> getAll() {
        try {
            log.info("Get all user");
            List<User> user = userRepository.findAll();
            if (user.isEmpty()) {
                log.info("user is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, courses, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by get all user, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return userRepository.findAll();
    }

    public Optional <User> findById( Long id) {
        return userRepository.findById(id);
    }

    public List <User> findByCity( String kota) {
        return userRepository.searchByCity(kota);
    }
    public List <User> getCitizen( ) {
        return userRepository.findCitizen();
    }
    public List <User> getHealth() {
        return userRepository.findHealth();
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
