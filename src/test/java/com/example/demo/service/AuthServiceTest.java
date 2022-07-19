package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.demo.entity.Role;
import com.example.demo.entity.RoleEnum;
import com.example.demo.entity.User;
import com.example.demo.entity.dto.UserDTO;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.TokenResponse;
import com.example.demo.security.JwtTokenProvider;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest(classes = AuthService.class)
public class AuthServiceTest {
    private final EasyRandom EASY_RANDOM = new EasyRandom();
    private User user;
    private List<User> users;
    private UserDTO userDTO;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = EASY_RANDOM.nextObject(User.class);
        users = EASY_RANDOM.objects(User.class, 2)
                .collect(Collectors.toList());
        userDTO = EASY_RANDOM.nextObject(UserDTO.class);
    }

    @Test
    void testGenerateToken_Success() {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userDTO.getEmail().toLowerCase(), userDTO.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);

        when(userRepository.findUsername(userDTO.getEmail().toLowerCase()))
            .thenReturn(user);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(jwt);

        var result = authService.generateToken(userDTO);

        assertEquals(tokenResponse, result);

    }
    @Test
    void testGenerateToken_Exception() {
        UserDTO dto = new UserDTO();
       
            assertThrows(RuntimeException.class, () -> {
                authService.generateToken(dto);
            });
        
    }

    @Test
    void testRegisterAdmin() {

        User user = new User();

        user.setNama(userDTO.getNama());
        user.setUsername(userDTO.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setImage(userDTO.getImage());
        user.setAddress(userDTO.getAddress());
        user.setKota(userDTO.getKota());
        user.setNoHp(userDTO.getNoHp());

        userDTO.setRoles(null);

        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        when(roleRepository.findByName(RoleEnum.ROLE_ADMIN))
            .thenReturn(Optional.of(role));
        
        roles.add(role);
        user.setRoles(roles);
        when(userRepository.save(user)).thenReturn(user);

        var result = authService.registerAdmin(userDTO);

        assertEquals(user, result);
    }
    @Test
    void registerAdmin_ExceptionTest() {
        when(userRepository.findUsername(userDTO.getEmail()))
            .thenReturn(user);

        assertThrows(RuntimeException.class, () -> {
            authService.registerAdmin(userDTO);
        });
    }
 
    @Test
    void registerAdmin_ExceptionTest2() {
        User user = new User();

        user.setNama(userDTO.getNama());
        user.setUsername(userDTO.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setImage(userDTO.getImage());
        user.setAddress(userDTO.getAddress());
        user.setKota(userDTO.getKota());
        user.setNoHp(userDTO.getNoHp());

        userDTO.setRoles(null);

        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        when(roleRepository.findByName(RoleEnum.ROLE_USER))
            .thenReturn(Optional.of(role));
        
        roles.add(role);

        user.setRoles(roles);


        assertThrows(RuntimeException.class, () -> {
            authService.registerAdmin(userDTO);
        });
    }

    

    @Test
    void testRegisterUser() {
        User user = new User();

        user.setNama(userDTO.getNama());
        user.setUsername(userDTO.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setImage(userDTO.getImage());
        user.setNik(userDTO.getNik());
        user.setGender(userDTO.getGender());
        user.setTglLahir(userDTO.getTglLahir());
        user.setAddress(userDTO.getAddress());
        user.setKota(userDTO.getKota());
        user.setNoHp(userDTO.getNoHp());

        userDTO.setRoles(null);

        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        when(roleRepository.findByName(RoleEnum.ROLE_USER))
            .thenReturn(Optional.of(role));
        
        roles.add(role);
        user.setRoles(roles);
        when(userRepository.save(user)).thenReturn(user);

        var result = authService.registerUser(userDTO);

        assertEquals(user, result);
    }
    @Test
    void registerUser_ExceptionTest() {
        when(userRepository.findUsername(userDTO.getEmail()))
            .thenReturn(user);

        assertThrows(RuntimeException.class, () -> {
            authService.registerUser(userDTO);
        });
    }
    @Test
    void registerUser_ExceptionTest2() {
        User user = new User();

        user.setNama(userDTO.getNama());
        user.setUsername(userDTO.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setImage(userDTO.getImage());
        user.setNik(userDTO.getNik());
        user.setGender(userDTO.getGender());
        user.setTglLahir(userDTO.getTglLahir());
        user.setAddress(userDTO.getAddress());
        user.setKota(userDTO.getKota());
        user.setNoHp(userDTO.getNoHp());

        userDTO.setRoles(null);

        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        when(roleRepository.findByName(RoleEnum.ROLE_ADMIN))
            .thenReturn(Optional.of(role));
        
        roles.add(role);
        user.setRoles(roles);

        assertThrows(RuntimeException.class, () -> {
            authService.registerUser(userDTO);
        });
    }
    

}
