package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.entity.User;
import com.example.demo.entity.dto.UserDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UserService.class)
public class UserServiceTest {
    private final EasyRandom EASY_RANDOM = new EasyRandom(); 
    private Long id;
    private List<User> users;
    private UserDTO userDto;
    private User user;
    

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        id = EASY_RANDOM.nextObject(Long.class);
        users = EASY_RANDOM.objects(User.class, 2)
                    .collect(Collectors.toList());
       userDto = EASY_RANDOM.nextObject(UserDTO.class);
        user= EASY_RANDOM.nextObject(User.class);
    }

    @Bean public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserService userService;

    @Test
    void testChangePassword_Success() {
        when(userRepository.findByUsername(userDto.getEmail()))
        .thenReturn(Optional.of(user));

    when(passwordEncoder.matches(userDto.getCurrentPassword(), user.getPassword()))
        .thenReturn(true);

    var result = userService.changePassword(userDto);

    assertEquals(user, result);
    }
    @Test
    void changePasswordExceptionTest() {
        assertThrows(RuntimeException.class, () -> {
            userService.changePassword(userDto);
        });
    }

    @Test
    void deleteSuccess_Test() {
        doNothing()
           .when(userRepository).deleteById(id);

        userService.deleteUser(id);

        verify(userRepository).deleteById(id);
    }

    @Test
    void deleteException_Test() {
        doThrow(RuntimeException.class)
            .when(userRepository).deleteById(id);
      
        assertThrows(RuntimeException.class, () -> {
            userService.deleteUser(id);
        });
    }

    @Test
    void testFindByCity() {

        when(userRepository.searchByCity(user.getKota()))
            .thenReturn(users);
        
        var result = userService.findByCity(user.getKota());

        assertEquals(users, result);

    }
    @Test
    void getByCityException_Test() {
        assertThrows(RuntimeException.class, () -> {
            userService.findByCity(user.getKota());
        });
    }

    @Test
    void testFindByIdSucces() {

        when(userRepository.findById(user.getIdUser()))
            .thenReturn(Optional.of(user));
        
        var result = userService.findById(user.getIdUser());

        assertEquals(user, result);
    }
    @Test
    void getIdException_Test() {
        assertThrows(RuntimeException.class, () -> {
            userService.findById(id);
        });
    }


    @Test
    void testGetAllSuccess() {
                
        when(userRepository.findAll())
            .thenReturn(users);
        
        var result = userService.getAll();

        verify(userRepository, times(1)).findAll();

        assertEquals(users, result);
    }

    @Test
    void getAllException_Test() {
        assertThrows(RuntimeException.class, () -> {
            userService.getAll();
        });
    }

    @Test
    void testGetCitizen() {
        when(userRepository.findCitizen())
            .thenReturn(users);
        
        var result = userService.getCitizen();

        verify(userRepository, times(1)).findCitizen();

        assertEquals(users, result);
    }
    @Test
    void getCitizenException_Test() {
        assertThrows(RuntimeException.class, () -> {
            userService.getCitizen();
        });
    }

    @Test
    void testGetHealth() {
        when(userRepository.findHealth())
            .thenReturn(users);
        
        var result = userService.getHealth();

        verify(userRepository, times(1)).findHealth();

        assertEquals(users, result);
    }
    @Test
    void getHealthException_Test() {
        assertThrows(RuntimeException.class, () -> {
            userService.getHealth();
        });
    }

    @Test
    void testSearchHealthById() {
        when(userRepository.searchHealthById(user.getIdUser()))
            .thenReturn(Optional.of(user));
        
        var result = userService.searchHealthById(user.getIdUser());

        assertEquals(user, result);

    }
    @Test
    void getHealthByIdException_Test() {
        assertThrows(RuntimeException.class, () -> {
            userService.searchHealthById(id);
        });
    }
    

    @Test
    void testUpdateHealth() {
        UserDTO userDto = new UserDTO();

        userDto = new UserDTO();
        userDto.setEmail("faskes@gmail.com");
        userDto.setNoHp("081");
        userDto.setNama("rs wirogunan");
        userDto.setAddress("JL Wirogunan");
        userDto.setKota("Yogyakarta");
        userDto.setImage("image.jpg");

       

        doReturn(Optional.of(user))
        .when(userRepository).searchHealthById(id);

        
        user.setUsername("faskes1@gmail.com");
        user.setNoHp("0812");
        user.setNama("rs wirogunan jogja");
        user.setAddress("JL Wirogunan 1");
        user.setKota("Yogyakarta");
        user.setImage("image.jpg");

        when(userRepository.save(user)).thenReturn(user);

        var resultHealth = userService.updateHealth(id, userDto);

        assertEquals(user, resultHealth);
    }



    @Test
    void updateHealthException_Test() {

        UserDTO userDTO = EASY_RANDOM.nextObject(UserDTO.class);

        assertThrows(RuntimeException.class, () -> {
            userService.updateHealth(id, userDTO);
        });
        
    }


    @Test
    void testUpdateUser() {
        UserDTO userDto = new UserDTO();

        userDto = new UserDTO();
        userDto.setEmail("a@gmail.com");
        userDto.setNik("123");
        userDto.setNoHp("081");
        userDto.setNama("arif");
        userDto.setGender("male");
        userDto.setTglLahir(new Date());
        userDto.setAddress("JL Wirogunan");
        userDto.setKota("Yogyakarta");
        userDto.setImage("image.jpg");

       

        doReturn(Optional.of(user))
        .when(userRepository).findById(id);

        user.setUsername("a@gmail.com");
        user.setNik("123");
        user.setNoHp("081");
        user.setNama("arif");
        user.setGender("male");
        user.setTglLahir(new Date());
        user.setAddress("JL Wirogunan");
        user.setKota("Yogyakarta");
        user.setImage("image.jpg");

        when(userRepository.save(user)).thenReturn(user);

        var result = userService.updateUser(id, userDto);

        assertEquals(user, result);
        
    }

    @Test
    void updateUSerException_Test() {

        UserDTO userDTO = EASY_RANDOM.nextObject(UserDTO.class);

        assertThrows(RuntimeException.class, () -> {
            userService.updateUser(id, userDTO);
        });
    }


}
