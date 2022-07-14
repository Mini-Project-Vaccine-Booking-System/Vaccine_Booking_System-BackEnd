package com.example.demo.service;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.entity.Role;
import com.example.demo.entity.RoleEnum;
import com.example.demo.entity.User;
import com.example.demo.entity.dto.UserDTO;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.TokenResponse;
import com.example.demo.security.JwtTokenProvider;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;


    public User registerAdmin(UserDTO req) { 
        try {
            log.info("Search username in database");
            if (userRepository.findUsername(req.getEmail()) != null) {
                throw new Exception("USER WITH EMAIL " + req.getEmail() + " IS ALREADY EXIST");
            }

            log.info("save new user");
            User user = new User();

            user.setNama(req.getNama());
            user.setUsername(req.getEmail().toLowerCase());            
            user.setPassword(passwordEncoder.encode(req.getPassword()));
            user.setImage(req.getImage());
            user.setAddress(req.getAddress());
            user.setKota(req.getKota());
            user.setNoHp(req.getNoHp());

            
            log.info("role");

            Set<Role> roles = new HashSet<>();
            if(req.getRoles() == null) {
                Role role = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("ROLE NOT FOUND"));
                
                roles.add(role);
            }
                user.setRoles(roles);
                log.info("role: " +roles);
                log.info("user role: "+user.getRoles());
            userRepository.save(user);

            req.setPassword("*".repeat(req.getPassword().length()));
            log.info("User {} saved", req.getEmail());
            return user;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public User registerUser(UserDTO req) { 
        try {
            log.info("Search username in database");
            if (userRepository.findUsername(req.getEmail()) != null) {
                throw new Exception("USER WITH EMAIL " + req.getEmail() + " IS ALREADY EXIST");
            }

            log.info("save new user");
            User user = new User();

            user.setNama(req.getNama());
            user.setUsername(req.getEmail().toLowerCase());            
            user.setPassword(passwordEncoder.encode(req.getPassword()));
            user.setImage(req.getImage());
            user.setNik(req.getNik());
            user.setGender(req.getGender());
            user.setTglLahir(req.getTglLahir());
            user.setAddress(req.getAddress());
            user.setKota(req.getKota());
            user.setNoHp(req.getNoHp());

            
            log.info("role");

            Set<Role> roles = new HashSet<>();
                if(req.getRoles() == null) {
                    Role role = roleRepository.findByName(RoleEnum.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("ROLE NOT FOUND"));
                    
                    roles.add(role);
                }
                user.setRoles(roles);
            userRepository.save(user);

            req.setPassword("*".repeat(req.getPassword().length()));
            log.info("User {} saved", req.getEmail());
            return user;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public TokenResponse generateToken(UserDTO req) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail().toLowerCase(), req.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtTokenProvider.generateToken(authentication);
            User user = userRepository.findUsername(req.getEmail());



            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setToken(jwt);
            tokenResponse.setUser(user);
            
            

            log.info("Token created");
            return tokenResponse;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

   
}
