package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.User;

import com.example.demo.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CitizenController {
    @Autowired
    private UserRepository citizenRepository;


    @GetMapping("/user")
    public List<User> getCitizens() {
        return citizenRepository.findAll();
    }
    @GetMapping("/user/{id}")
    public Optional <User> getCitizen(@PathVariable Long id) {
        return citizenRepository.findById(id);
    }
    @PostMapping("/user")
    public User createNewCitizen(@RequestBody User payload) {
        return citizenRepository.save(payload);
    }
    @PutMapping("/user/{id}") 
    public Optional<User> updateCitizen(
        @PathVariable Long id, 
        @RequestBody User  citizen) {
            Optional<User> citizenById = citizenRepository.findById(id);
        
        citizenById.ifPresent(res -> {
            res.setNik(citizen.getNik());
            res.setNo_hp(citizen.getNo_hp());
            res.setNama(citizen.getNama());
            res.setGender(citizen.getGender());
            res.setTgl_lahir(citizen.getTgl_lahir());
            res.setImage(citizen.getImage());
            res.setUsername(citizen.getUsername());
            res.setPassword(citizen.getPassword());
            res.setUpdatedAt(citizen.getUpdatedAt());
            citizenRepository.save(res);
        });
        return citizenById;
    }
    @DeleteMapping("/user/{id}")
    public void deleteCitizen(@PathVariable Long id) {
        Optional<User> citizenById = citizenRepository.findById(id);
        citizenById.ifPresent(res -> {
            citizenRepository.delete(res);
        });
    }
    // private UserServi userServi;
    // public CitizenController(UserServi userServi) {
    //     this.userServi = userServi;
    // }
    // @GetMapping("/user/search")
    // public ResponseEntity<List<User>> seacrhUsers(@RequestParam("query") String query) {
    //     return ResponseEntity.ok(userServi.searchUsers(query));
    // }
   
    // @GetMapping("/citizenDTO")
    // public List<UserDTO> getCitizenDTO() {
    //     List<User> citizen = citizenRepository.findAll();
    //     List<UserDTO> citizenDTO = new ArrayList<>();
    //     citizen.forEach(res -> {
    //         CitizenDTO citizen1 = new CitizenDTO();
    //         citizen1.setId_user(res.getId_user());
    //         citizen1.setKk(res.getKk());
    //         citizen1.setNo_hp(res.getNo_hp());
    //         citizen1.setNama_user(res.getNama_user());
    //         citizen1.setGender(res.getGender());
    //         citizen1.setTgl_lahir(res.getTgl_lahir());
    //         citizen1.setAddress(res.getAddress());
    //         citizen1.setUsername(res.getUsername());
    //         citizen1.setRole(res.getRole());
    //         citizen1.setUpdated_at(res.getUpdated_at());
    //         citizen1.setCreated_at(res.getCreated_at());
    //         citizenDTO.add(citizen1);
    //     });
    //     return citizenDTO;
    // }

    
}
