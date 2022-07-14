package com.example.demo.controller;

import java.util.List;

import com.example.demo.constant.AppConstant;
import com.example.demo.entity.Booking;
import com.example.demo.entity.dto.BookingDTO;
import com.example.demo.service.BookingService;
import com.example.demo.util.ResponseUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getBooking() {
        try {
            List<Booking> bookings =  bookingService.getBooking();
            return ResponseUtil.build("GET All BOOKING",AppConstant.ResponseCode.SUCCESS, bookings, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getBooking(@PathVariable(value = "id") Long id) {
        try {
            Booking booking =  bookingService.getBookingById(id);
            return ResponseUtil.build("GET BOOKING DETAIL",AppConstant.ResponseCode.SUCCESS, booking, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }  
    }


    @GetMapping(value = "/s/{idUser}")
    public ResponseEntity<?> findBookingByUserId(@PathVariable(value = "idUser") Long idUser) {
        try {
            List<Booking> bookings =  bookingService.getByUserId(idUser);
            return ResponseUtil.build("GET BOOKING BY USER ID ",AppConstant.ResponseCode.SUCCESS, bookings, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
            } 
    }


    @GetMapping(value = "/user/{idUser}")
    public ResponseEntity<?> findBookingByUserHealthId(@PathVariable(value = "idUser") Long idUser) {
        try {
            List<Booking> bookings =  bookingService.getByUserHealthId(idUser);
            return ResponseUtil.build("GET BOOKING BY HEALTH FACILITY ID ",AppConstant.ResponseCode.SUCCESS, bookings, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
            }  
    }

    @PutMapping(value = "/{id}") 
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateBooking(
        @PathVariable Long id, @RequestBody BookingDTO  request) {
            try {
                Booking booking = bookingService.updateBooking(id, request);
                return ResponseUtil.build("BOOKING ID " + id + " UPDATED",AppConstant.ResponseCode.SUCCESS, booking, HttpStatus.OK);
            } catch (Exception e) {
                return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
            } 
    }

    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createNewBooking(@RequestBody BookingDTO request) {
        try {
            Booking booking = bookingService.save(request);
            return ResponseUtil.build("BOOKING CREATED",AppConstant.ResponseCode.SUCCESS, booking, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteBooking(@PathVariable (value = "id") Long id) {
        try {
            bookingService.deleteBooking(id);
            return ResponseUtil.build("BOOKING ID " + id + " DELETED",AppConstant.ResponseCode.SUCCESS, null, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseUtil.build(e.getMessage(),AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }


   
}
