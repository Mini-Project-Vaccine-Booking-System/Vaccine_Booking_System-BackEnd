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

    public List<User> getCitizens() {
        return userRepository.findAll();
    }

    public Optional <User> findById( Long id) {
        return userRepository.findById(id);
    }

    public Optional <User> findByCity( String kota) {
        return userRepository.searchByCity(kota);
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
            userRepository.delete(res);System.out.println("Deleted");
        }.orElse(System.out.println("Not Found")));
        
        
    }
    // public void deleteCitizen( Long id) {
    //     try {
    //     userRepository.findById(id)
    //         .orElseThrow(()-> new Exception("User ID" + id + "Not Found"));
    //        userRepository.delete(id); 
    //     } catch (Exception e) {
    //         log.error ("Delete user error");
    //         throw new RuntimeException(e.getMessage(), e);
    //     }
        
        
    // }
}
