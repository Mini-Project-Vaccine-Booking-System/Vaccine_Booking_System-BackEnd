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

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping(value = "")
    public List<Booking> getBooking() {
        return bookingService.getBooking();
    }
    
    @GetMapping(value = "/{id}")
    public Optional <Booking> getBooking(@PathVariable(value = "id") Long id) {
        return bookingService.getBookingById(id);
    }

    @PostMapping("")
    public Booking createNewBooking(@RequestBody BookingDTO request) {
      return bookingService.save(request);
  }

    @DeleteMapping(value = "/{id}")
    public String deleteBooking(@PathVariable (value = "id") Long id) {
      return bookingService.deleteBooking(id);
    }
    @PutMapping(value = "/{id}") 
    public Optional<Booking> updateBooking(
        @PathVariable Long id, @RequestBody BookingDTO  request) {
            return bookingService.updateBooking(id, request);
    }
}