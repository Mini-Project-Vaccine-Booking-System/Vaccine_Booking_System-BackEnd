package com.example.demo.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.demo.entity.Booking;
import com.example.demo.entity.dto.BookingDTO;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.KelompokRepository;
import com.example.demo.repository.SessionRepository;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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
@SpringBootTest(classes = BookingService.class)
public class BookingServiceTest {
    private final EasyRandom EASY_RANDOM = new EasyRandom(); 
    private Long id;
    private Booking booking;
    private List<Booking> bookings;
    private BookingDTO bookingDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        id = EASY_RANDOM.nextObject(Long.class);
        bookings = EASY_RANDOM.objects(Booking.class, 2)
                    .collect(Collectors.toList());
        bookingDTO = EASY_RANDOM.nextObject(BookingDTO.class);
        booking= EASY_RANDOM.nextObject(Booking.class);
        
    }

    @Autowired
    private BookingService bookingService;
    @MockBean
    private KelompokService kelompokService; 
    @MockBean
    private SessionService sessionService;
    @MockBean
    private SessionRepository sessionRepository;
    @MockBean
    private KelompokRepository kelompokRepository;
    @MockBean
    private BookingRepository bookingRepository;


    @Test
    void testDeleteBooking() {
        when(bookingRepository.findById(booking.getIdBooking()))
        .thenReturn(Optional.of(booking));
    
       
        doNothing()
           .when(bookingRepository).deleteById(booking.getIdBooking());

           bookingService.deleteBooking(booking.getIdBooking());

            verify(bookingRepository).deleteById(booking.getIdBooking());
    }
    @Test
    void deleteException_Test() {
        doThrow(RuntimeException.class)
            .when(bookingRepository).deleteById(id);
      
        assertThrows(RuntimeException.class, () -> {
            bookingService.deleteBooking(id);
        });
    }

    @Test
    void testGetBooking_Success() {
        when(bookingRepository.findAll())
        .thenReturn(bookings);
    
        var result = bookingService.getBooking();

        verify(bookingRepository, times(1)).findAll();

        assertEquals(bookings, result);
    }

    @Test
    void getAllException_Test() {
        assertThrows(RuntimeException.class, () -> {
            bookingService.getBooking();
        });
    }

    @Test
    void testGetBookingById_Success() {
        when(bookingRepository.findById(booking.getIdBooking()))
        .thenReturn(Optional.of(booking));
    
        var result = bookingService.getBookingById(booking.getIdBooking());

        assertEquals(booking, result);

    }
    @Test
    void getIdException_Test() {
        assertThrows(RuntimeException.class, () -> {
            bookingService.getBookingById(id);
        });
    }
    @Test
    void testGetByUserHealthId_Success() {
        when( bookingRepository.findBySession_User_IdUser(id))
        .thenReturn(bookings);
    
            var result = bookingService.getByUserHealthId(id);


            assertEquals(bookings, result);
        }
    @Test
        void getByUserHealthIdException_Test() {
            assertThrows(RuntimeException.class, () -> {
                bookingService.getByUserHealthId(id);
            });
        }

    @Test
    void testGetByUserId() {
        when( bookingRepository.findByKelompok_User_IdUser(id))
        .thenReturn(bookings);
    
            var result = bookingService.getByUserId(id);


            assertEquals(bookings, result);
        }
    @Test
        void getByUserIdException_Test() {
            assertThrows(RuntimeException.class, () -> {
                bookingService.getByUserId(id);
            });
        }

    @Test
    void testSave() {

    }

    @Test
    void testUpdateBooking() {

    }
}
