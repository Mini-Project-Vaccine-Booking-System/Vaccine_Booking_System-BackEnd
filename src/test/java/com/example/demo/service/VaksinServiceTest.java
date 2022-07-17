package com.example.demo.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.entity.Vaksin;
import com.example.demo.entity.dto.VaksinDTO;
import com.example.demo.repository.VaksinRepository;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = VaksinService.class)
public class VaksinServiceTest {
    private final EasyRandom EASY_RANDOM = new EasyRandom(); 
    private Long id;
    private Vaksin vaksin;
    private List<Vaksin> vaksins;
    private VaksinDTO vaksinDTO;

    @Autowired
    private VaksinService vaksinService;
    @MockBean
    VaksinRepository vaksinRepository;
    @MockBean
    private UserService userService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        id = EASY_RANDOM.nextObject(Long.class);
        vaksins = EASY_RANDOM.objects(Vaksin.class, 2)
                    .collect(Collectors.toList());
        vaksinDTO = EASY_RANDOM.nextObject(VaksinDTO.class);
        vaksin= EASY_RANDOM.nextObject(Vaksin.class);
        
    }

    
    @Test
    void testDeleteVaksin_Success() {
        when(vaksinRepository.findById(vaksin.getIdVaksin()))
        .thenReturn(Optional.of(vaksin));
    
       
        doNothing()
           .when(vaksinRepository).deleteById(vaksin.getIdVaksin());

            vaksinService.deleteVaksin(vaksin.getIdVaksin());

            verify(vaksinRepository).deleteById(vaksin.getIdVaksin());
    }
    @Test
    void deleteException_Test() {
        doThrow(RuntimeException.class)
            .when(vaksinRepository).deleteById(id);
      
        assertThrows(RuntimeException.class, () -> {
            vaksinService.deleteVaksin(id);
        });
    }

    @Test
    void testFindById() {

    }

    @Test
    void testGetByUserId() {

    }

    @Test
    void testGetvaksin() {

    }

    @Test
    void testSave() {

    }

    @Test
    void testUpdateVaksin() {

    }
}
