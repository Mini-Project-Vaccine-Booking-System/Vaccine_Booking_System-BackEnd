package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.User;
import com.example.demo.entity.dto.UserDTO;
import com.example.demo.service.UserService;

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
@RequestMapping("/user")
public class CitizenController {
    @Autowired
    private UserService userService;


    @GetMapping(value = "")
    public List<User> getCitizens() {
        return userService.getCitizens();
    }
    @GetMapping(value = "/{id}")
    public Optional <User> getCitizen(@PathVariable(value = "id") Long id) {
        return userService.findById(id);
    }
    @PostMapping(value = "")
    public User createNewCitizen(@RequestBody User request) {
        return userService.save(request);
    }

    @PutMapping(value = "/{id}") 
    public Optional<User> updateCitizen(
        @PathVariable (value = "id") Long id, @RequestBody UserDTO  citizen) {
            return userService.updateCitizen(id, citizen);
    }
    @DeleteMapping(value = "/{id}")
    public String deleteCitizen(@PathVariable (value = "id") Long id) {
      return userService.deleteCitizen(id);
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
