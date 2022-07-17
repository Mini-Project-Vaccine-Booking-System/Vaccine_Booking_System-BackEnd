package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.entity.Session;
import com.example.demo.entity.dto.SessionDTO;
import com.example.demo.repository.SessionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VaksinRepository;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SessionService.class)
public class SessionServiceTest {
    private final EasyRandom EASY_RANDOM = new EasyRandom(); 
    private Long id;
    private Session session;
    private List<Session> sessions;
    private SessionDTO sessionDTO;

    java.util.Date utilDate = new java.util.Date();
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

    @Autowired
    private SessionService sessionService;
    @MockBean
    private UserService userService;
    @MockBean
    private VaksinService vaksinService;
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private SessionRepository sessionRepository;
    @MockBean
    private VaksinRepository vaksinRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        id = EASY_RANDOM.nextObject(Long.class);
        sessions = EASY_RANDOM.objects(Session.class, 2)
                    .collect(Collectors.toList());
        sessionDTO = EASY_RANDOM.nextObject(SessionDTO.class);
        session= EASY_RANDOM.nextObject(Session.class);
        
    }


    @Test
    void testDeleteSession_Success() {
        when(sessionRepository.findById(session.getIdSession()))
        .thenReturn(Optional.of(session));
    
       
        doNothing()
           .when(sessionRepository).deleteById(session.getIdSession());

            sessionService.deleteSession(session.getIdSession());

            verify(sessionRepository).deleteById(session.getIdSession());
    }
    @Test
    void deleteException_Test() {
        doThrow(RuntimeException.class)
            .when(sessionRepository).deleteById(id);
      
        assertThrows(RuntimeException.class, () -> {
            sessionService.deleteSession(id);
        });
    }

    @Test
    void testGetByDate_Success() {


        when(sessionRepository.findByDate(sqlDate))
        .thenReturn(sessions);
    
    var result = sessionService.getByDate(sqlDate);


    assertEquals(sessions, result);
    }
    @Test
    void getByDateException_Test() {

        assertThrows(RuntimeException.class, () -> {
            sessionService.getByDate(sqlDate);
        });
    }

    @Test
    void testGetByDateAndUser_kota_Success() {
        when(sessionRepository.findByDateAndUser_kota(sqlDate, session.getUser().getKota()))
        .thenReturn(sessions);
    
    var result = sessionService.getByDateAndUser_kota(sqlDate, session.getUser().getKota());


    assertEquals(sessions, result);
    }
    @Test
    void getByDateAndUserException_Test() {

        assertThrows(RuntimeException.class, () -> {
            sessionService.getByDateAndUser_kota(sqlDate, session.getUser().getKota());
        });
    }

    @Test
    void testGetByUserId_Success() {
        when(sessionRepository.findByUser_idUser(session.getUser().getIdUser()))
        .thenReturn(sessions);
    
        var result = sessionService.getByUserId(session.getUser().getIdUser());


        assertEquals(sessions, result);
    }
    @Test
    void getByUserIdException_Test() {
        assertThrows(RuntimeException.class, () -> {
            sessionService.getByUserId(session.getUser().getIdUser());
        });
    }

    @Test
    void testGetSession_Success() {
        when(sessionRepository.findAll())
        .thenReturn(sessions);
    
        var result = sessionService.getSession();

        verify(sessionRepository, times(1)).findAll();

        assertEquals(sessions, result);
        }

    @Test
    void getAllException_Test() {
        assertThrows(RuntimeException.class, () -> {
            sessionService.getSession();
        });
    }
    @Test
    void testGetSessionById() {
        when(sessionRepository.findById(session.getIdSession()))
        .thenReturn(Optional.of(session));
        
        var result = sessionService.getSessionById(session.getIdSession());

        assertEquals(session, result);

    }
    @Test
    void getIdException_Test() {
        assertThrows(RuntimeException.class, () -> {
            sessionService.getSessionById(id);
        });
    }

    @Test
    void testSave() {

    }

    @Test
    void testUpdateSession() {

    }
}
