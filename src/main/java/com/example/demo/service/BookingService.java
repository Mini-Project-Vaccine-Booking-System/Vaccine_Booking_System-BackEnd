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
    KelompokRepository kelompokRepository;

    @Autowired
    SessionRepository sessionRepository;


    public ResponseEntity<Object> getBooking() {
        try {
            log.info("Get all booking");
            List<Booking> booking = bookingRepository.findAll();
            if (booking.isEmpty()) {
                log.info("booking is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, booking, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error by get all booking, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getBookingById(Long id) {
        try {
            log.info("Get booking by id");
            Optional<Booking> bookingById = bookingRepository.findById(id);
            if (bookingById.isEmpty()) {
                log.info("booking is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, bookingById, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error in get booking by id , Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Object> getByUserId( Long idUser) {
        try {
            log.info("Get booking by kelompok parent's id");
            List<Booking> bookingByParent = bookingRepository.findByKelompok_User_IdUser(idUser);
            if (bookingByParent.isEmpty()) {
                log.info("booking is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, bookingByParent, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error in get booking by kelompok' parent's id , Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Object> getByUserHealthId( Long idUser) {
        try {
            log.info("Get booking by session health's id");
            List<Booking> bookingBySesParent = bookingRepository.findBySession_User_IdUser(idUser);
            if (bookingBySesParent.isEmpty()) {
                log.info("booking is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, bookingBySesParent, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Get an error in get booking by session' health's id , Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> save(BookingDTO request) {
        try{    
            log.info("save new booking: {}", request);
            
            log.info("search kelompok  id {}", request.getIdKelompok());
            Optional <Kelompok> kelompok = kelompokRepository.findById(request.getIdKelompok());
            if(kelompok.isEmpty()) {
                log.info("kelompok booking id: ", request.getIdKelompok()," is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            log.info("search session  id {}", request.getIdSession());
            Optional<Session> session = sessionRepository.findById(request.getIdSession());
            if(session.isEmpty()) {
                log.info("session booking id: ",request.getIdSession()," is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }           
            log.info("save booking");
            Booking booking = new Booking();
            booking.setKelompok(kelompok.get());
            booking.setSession(session.get());
            bookingRepository.save(booking);
    
            return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, booking, HttpStatus.OK);
        }catch (Exception e) {
            log.error("Get an error by executing create new booking, Error : {}",e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateBooking( Long id, BookingDTO request) {
        try{    
            log.info("search booking: ");
            Optional <Booking> booking = bookingRepository.findById(id);
            log.info("search kelompok booking update  id {}", request.getIdSession());
            Optional <Kelompok> kelompok = kelompokRepository.findById(request.getIdKelompok());
            if(kelompok.isEmpty()) {
                log.info("kelompok booking update id: ", request.getIdKelompok()," is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            log.info("search session booking update  id {}", request.getIdSession());
            Optional<Session> session = sessionRepository.findById(request.getIdSession());
            if(session.isEmpty()) {
                log.info("session booking update id: ",request.getIdSession()," is empty");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }   
            log.info("Update session: {}", request);
        booking.ifPresent(res -> {
            res.setKelompok(kelompok.get());
            res.setSession(session.get());
            bookingRepository.save(res);
        });
        return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, booking, HttpStatus.OK);
    }catch (Exception e) {
        log.error("Get an error by update booking, Error : {}",e.getMessage());
        return ResponseUtil.build(AppConstant.ResponseCode.UNKNOWN_ERROR,null,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    public ResponseEntity<Object> deleteBooking(Long id) {
        try {
            log.info("Check by Booking id: "+id);
            Optional<Booking> bookingById = bookingRepository.findById(id);
            if(bookingById.isEmpty()){
                log.info("Booking id "+id+ " NOT FOUND");
                return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
            }
            log.info("Executing delete booking by id: {}", id);
            bookingById.ifPresent(res -> {
                bookingRepository.delete(res);
            });
        } catch (EmptyResultDataAccessException e) {
            log.error("Data not found. Error: {}", e.getMessage());
            return ResponseUtil.build(AppConstant.ResponseCode.DATA_NOT_FOUND, null, HttpStatus.NOT_FOUND);
        }
        return ResponseUtil.build(AppConstant.ResponseCode.SUCCESS, null, HttpStatus.OK); 
    }

    
}
