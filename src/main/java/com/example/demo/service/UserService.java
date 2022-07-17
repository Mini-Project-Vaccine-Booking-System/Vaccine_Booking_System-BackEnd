package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.demo.entity.User;
import com.example.demo.entity.dto.UserDTO;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

        

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    
    public List <User> getAll(){
        try {
            log.info("Get all user");
            List<User> user = userRepository.findAll();
            if (user.isEmpty()) {
                log.info("user is empty");
                throw new Exception("USER IS EMPTY");
            }
            return user;
        } catch (Exception e) {
            log.error("Get an error by get all user, Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

     public User findById(Long id) {
        try {
            log.info("Get user detail");
             User user = userRepository.findById(id)
             .orElseThrow(() -> new Exception("USER ID " + id + " NOT FOUND"));
              return user;
              } catch (Exception e) {
            log.error("Get an error by get user detail, Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public List<User> findByCity( String kota) {
        try {
            log.info("Get health detail by city");
            List<User> userCity = userRepository.searchByCity(kota);
            if (userCity.isEmpty()) {
                log.info("user is empty");
                throw new Exception("USER IS EMPTY");
            }
            return userCity;
        } catch (Exception e) {
            log.error("Get an error by get user health by city, Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public List<User> getCitizen() {
        try {
            log.info("Get all citizen");
            List<User> userCitizen = userRepository.findCitizen();
            if (userCitizen.isEmpty()) {
                log.info("citizen is empty");
                throw new Exception("CITIZEN IS EMPTY");
            }
            return userCitizen;

        } catch (Exception e) {
            log.error("Get an error by get all citizen, Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e);          

        }
    }


    public List<User>  getHealth() {
        try {
            log.info("Get all health facilities");
            List<User> userHealth = userRepository.findHealth();
            if (userHealth.isEmpty()) {
                log.info("health facilities is empty");
                throw new Exception("HEALTH FACILITIES IS EMPTY");   

            }
            return userHealth;

        } catch (Exception e) {
            log.error("Get an error by get all health facilities, Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e);

        }
    }

    public User searchHealthById(Long id){
        try {
            log.info("Get user detail");
             User user = userRepository.searchHealthById(id)
             .orElseThrow(() -> new Exception("HEALTH FACILITY ID " + id + " NOT FOUND"));
              return user;
              } catch (Exception e) {
            log.error("Get an error by get user detail, Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    public User updateUser(Long id,UserDTO  citizen) {
            try {
                log.info("Update user: {}", citizen.getNama());
                User citizenById = userRepository.findById(id)
                    .orElseThrow(() -> new Exception("USER ID " + id +" NOT FOUND"));
        
            citizenById.setNik(citizen.getNik());
            citizenById.setNoHp(citizen.getNoHp());
            citizenById.setNama(citizen.getNama());
            citizenById.setGender(citizen.getGender());
            citizenById.setTglLahir(citizen.getTglLahir());
            citizenById.setAddress(citizen.getAddress());
            citizenById.setKota(citizen.getKota());
            citizenById.setImage(citizen.getImage());
            citizenById.setUsername(citizen.getEmail().toLowerCase());
            citizenById.setUpdatedAt(citizen.getUpdated_at());
            userRepository.save(citizenById);

            return citizenById;
        } catch (Exception e) {
            log.error("Get an error by update citizen, Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

   
        public User updateHealth(Long id,UserDTO  health) {
            try {
                log.info("Update user: {}", health.getNama());
                User healthById = userRepository.searchHealthById(id).get();
                if (healthById==null) {
                    log.info("health facilities not found");
                    throw new Exception("HEALTH FACILITY IS NOT FOUND");
                }

            healthById.setNoHp(health.getNoHp());
            healthById.setNama(health.getNama());
            healthById.setAddress(health.getAddress());
            healthById.setKota(health.getKota());
            healthById.setImage(health.getImage());
            healthById.setUsername(health.getEmail().toLowerCase());
            healthById.setUpdatedAt(health.getUpdated_at());
            userRepository.save(healthById);
            return healthById;
        } catch (Exception e) {
            log.error("Get an error by update health facility, Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
        }


    public void deleteUser(Long id) {
        try {
            log.info("Executing delete user by id: {}", id);
            userRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Delete user error");
            throw new RuntimeException(e.getMessage(), e);
        }
       
    }

        public User changePassword(UserDTO request) {
        try {
            log.info("Find user: {}", request);
            Optional<User> user = userRepository.findByUsername(request.getEmail());
            if (user.isEmpty()) {
                log.info("user not found");
                throw new Exception("DATA NOT FOUND");
            }

            log.info("Check password");
            Boolean isMatch = passwordEncoder.matches(request.getCurrentPassword(), user.get().getPassword());
            if(!isMatch) throw new Exception("Password does not match");

            log.info("Save new password");
            user.get().setPassword(passwordEncoder.encode(request.getNewPassword()));
            
            userRepository.save(user.get());
            return user.get();
        } catch(Exception e) {
            log.error("Change password error, Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
