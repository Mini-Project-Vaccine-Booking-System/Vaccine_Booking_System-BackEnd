package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Booking;
import com.example.demo.entity.dto.BookingDTO;
import com.example.demo.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping(value = "")
    public ResponseEntity<Object> getBooking() {
        return bookingService.getBooking();
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getBooking(@PathVariable(value = "id") Long id) {
        return bookingService.getBookingById(id);
    }
    @GetMapping(value = "/s/{idUser}")
    public ResponseEntity<Object> findBookingByUserId(@PathVariable(value = "idUser") Long idUser) {
        return bookingService.getByUserId(idUser);
    }
    @GetMapping(value = "/user/{idUser}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> findBookingByUserHealthId(@PathVariable(value = "idUser") Long idUser) {
        return bookingService.getByUserHealthId(idUser);
    }

    @PutMapping(value = "/{id}") 
    public ResponseEntity<Object> updateBooking(
        @PathVariable Long id, @RequestBody BookingDTO  request) {
            return bookingService.updateBooking(id, request);
    }

    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> createNewBooking(@RequestBody BookingDTO request) {
            return bookingService.save(request);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteBooking(@PathVariable (value = "id") Long id) {
      return bookingService.deleteBooking(id);
    }


   
}
