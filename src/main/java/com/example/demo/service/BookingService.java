package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Booking;
import com.example.demo.entity.Kelompok;
import com.example.demo.entity.Session;
import com.example.demo.entity.Vaksin;
import com.example.demo.entity.dto.BookingDTO;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.KelompokRepository;
import com.example.demo.repository.SessionRepository;
import com.example.demo.repository.VaksinRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.util.*;
import com.example.demo.constant.*;
import com.example.demo.entity.base.*;
import org.springframework.http.*;
import org.springframework.dao.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    KelompokService kelompokService;
    @Autowired
    SessionService sessionService;

    @Autowired
    KelompokRepository kelompokRepository;

    @Autowired
    SessionRepository sessionRepository;


    public List<Booking> getBooking() {
        try {
            log.info("Get all booking");
            List<Booking> booking = bookingRepository.findAll();
            if (booking.isEmpty()) {
                log.info("booking is empty");
                throw new Exception("BOOKING IS EMPTY");
               
            }
            return booking;
            
        } catch (Exception e) {
            log.error("Get an error by get all booking, Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
            
        }
    }

    public Booking getBookingById(Long id) {
        try {
            log.info("Get booking by id");
            Booking bookingById = bookingRepository.findById(id)
                .orElseThrow(() -> new Exception("BOOKING ID " + id + " NOT FOUND"));
                return bookingById;
            
        } catch (Exception e) {
            log.error("Get an error in get booking by id , Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
            
        }
    }
    public List<Booking> getByUserId( Long idUser) {
        try {
            log.info("Get booking by kelompok parent's id");
            List<Booking> bookingByParent = bookingRepository.findByKelompok_User_IdUser(idUser);
            if (bookingByParent.isEmpty()) {
                log.info("booking is empty");
                throw new Exception("BOOKING BY USER ID "+ idUser+" IS EMPTY");
                
            }
            return bookingByParent;
         
            
        } catch (Exception e) {
            log.error("Get an error in get booking by kelompok' parent's id , Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
            
        }
    }
    public List<Booking> getByUserHealthId( Long idUser) {
        try {
            log.info("Get booking by session health's id");
            List<Booking> bookingBySesParent = bookingRepository.findBySession_User_IdUser(idUser);
            if (bookingBySesParent.isEmpty()) {
                log.info("booking is empty");
                throw new Exception("BOOKING BY HEALTH FACILITIES ID "+ idUser+" IS EMPTY");
                
            }
            return bookingBySesParent;
            
        } catch (Exception e) {
            log.error("Get an error in get booking by session' health's id , Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e); 
            
        }
    }

    public Booking save(BookingDTO request) {
        try{    
            log.info("save new booking: {}", request);
            
            log.info("search kelompok  id {}", request.getIdKelompok());
            Kelompok kelompok = kelompokService.findById(request.getIdKelompok());
            
            Optional<Booking> checkBooking = bookingRepository.findByKelompok_IdKelompok(request.getIdKelompok());
            if(checkBooking.isPresent()) {
                log.info("USER : " + request.getIdKelompok() + " HAS ALREADY BOOKED VACCINE: ");
                throw new Exception("USER HAS ALREADY BOOKED VACCINE"); }
                

            int stok = sessionRepository.sessionStok(request.getIdSession());
            if(stok>0) {
                log.info("session stok by id: " + request.getIdSession() + " before booking is: " + stok);
                sessionRepository.setStokMinus(request.getIdSession());  
            }
            log.info("search session  id {}", request.getIdSession());
            Session session = sessionService.getSessionById(request.getIdSession());
            
           
            log.info("save booking");
            Booking booking = new Booking();
            booking.setKelompok(kelompok);
            booking.setSession(session);
            bookingRepository.save(booking);
            return booking;
    
           
        }catch (Exception e) {
            log.error("Get an error by executing create new booking, Error : {}",e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
           
        }
    }

    public Booking updateBooking( Long id, BookingDTO request) {
        try{    
            log.info("search booking: ");
            Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new Exception("BOOKING ID " + id +" NOT FOUND"));
            
            log.info("search kelompok booking update  id {}", request.getIdSession());


            Kelompok kelompok = kelompokService.findById(request.getIdKelompok());
            


            log.info("search session booking update  id {}", request.getIdSession());
            Session session = sessionService.getSessionById(request.getIdSession());
              
            log.info("Update Booking: {}", request);
        
            booking.setKelompok(kelompok);
            booking.setSession(session);
            bookingRepository.save(booking);
            return booking;
        
        
    }catch (Exception e) {
        log.error("Get an error by update booking, Error : {}",e.getMessage());
        throw new RuntimeException(e.getMessage(), e);
        
    }
    }

    public void deleteBooking(Long id) {
        try {
            log.info("Check by Booking id: "+id);
            bookingRepository.findById(id)
                .orElseThrow(() -> new Exception("BOOKING ID " + id + " NOT FOUND"));
            
            log.info("Executing delete booking by id: {}", id);
            
                bookingRepository.deleteById(id);
           
        } catch (Exception e) {
            log.error("Data not found. Error: {}", e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
            
        }
        
    }

    
}
