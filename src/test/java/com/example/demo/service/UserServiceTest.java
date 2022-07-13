package com.example.demo.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.experimental.categories.Categories;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.demo.repository.UserRepository;

import net.bytebuddy.agent.VirtualMachine.ForHotSpot.Connection.Response;

import com.example.demo.entity.User;
import com.example.demo.entity.dto.UserDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes= UserService.class)
public class UserServiceTest {
    @MockBean
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private User user;

    // @Test
    // void getAll() {
    //     List<User> users = new ArrayList<>();
    //     users.add(user);
    //     when(userRepository.findAll()).thenReturn(users);
    //     ResponseEntity<Object> response = userService.getAll();
    //     assertEquals(HttpStatus.OK, response.getStatusCode());
    // }

    // @Test
    // void  findAll_Test() {
    //     List<User> userList = new ArrayList<>();
    //     User user = User.builder()
    //     .idUser(1L)
    //     .nama("name")
    //     .email("email")
    //     .password("password")
    //     .build();

    // userList.add(user);
    // Integer countUser = 1;
    // Integer countUserList = 2;

    // UserDTO userDTO = UserDTO.builder()
    // }
    
 

    // @Test
    // void findAll_Test() {
    //     List<User> userList = new ArrayList<>();
    //     User user = User.builder()
    //     .idUser(1L)
    //     .nama("name")
    //     .email("email")
    //     .password("password")
    //     .build();

    //     userList.add(user);
    //     Integer countUser = 1;
    //     Integer countUserList = 2;
    //     when(userRepository.findAll()).thenReturn(userList);
    //     assertEquals(countUserList, userService.findAll());
    // }
   
    @Test
    void findById_successTest() {
        User user = User.builder().idUser(1L).build();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        ResponseEntity<Object> response = userService.findById(1L);
        Optional<User> optionalUser = (Optional<User>) response.getBody();
        assertEquals(1L, optionalUser.get().getIdUser());
        
    }
    
    @Test
    void findById_failTest() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Object> response = userService.findById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    void findById_notFoundTest() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Object> response = userService.findById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    

    /**
     * 
     */

    
}
