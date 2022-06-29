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


    public List<Booking> getBooking() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public Booking save(BookingDTO request) {
        try{    
            Booking booking = new Booking();
            log.info("search kelompok  id {}", request.getIdKelompok());
            Kelompok kelompok = kelompokRepository.findById(request.getIdKelompok())
                .orElseThrow(()->  new Exception( " Id User" + request.getIdKelompok() + "Not Found"));
            Session session = sessionRepository.findById(request.getIdSession())
                .orElseThrow(()->  new Exception( " Id Session" + request.getIdSession()+ "Not Found"));
           
            log.info("save booking");
            booking.setKelompok(kelompok);
            booking.setSession(session);
            bookingRepository.save(booking);
    
            return booking;
        }
            catch(Exception e){
                log.error("save error");
                return null;
            }
    }

    public Optional<Booking> updateBooking( Long id, BookingDTO request) {
        try{    
            Optional <Booking> booking = bookingRepository.findById(id);
            Kelompok kelompok = kelompokRepository.findById(request.getIdKelompok())
                .orElseThrow(()->  new Exception( " Id User" + request.getIdKelompok() + "Not Found"));
                Session session = sessionRepository.findById(request.getIdSession())
                .orElseThrow(()->  new Exception( " Id Session" + request.getIdSession()+ "Not Found"));

        booking.ifPresent(res -> {
            res.setKelompok(kelompok);
            res.setSession(session);
            bookingRepository.save(res);
        });
        return booking;
    }
        catch(Exception e){
            return null;
        }
    }

    public String deleteBooking(Long id) {
        Optional<Booking> bookingById = bookingRepository.findById(id);
        bookingById.ifPresent(res -> {
            bookingRepository.delete(res);
        });
        if(bookingById.isPresent()){
            return "Delete Success";
        }
        else{
            return "Delete Failed";
        }
    }

    public List <Booking> getByUserId( Long idUser) {
        return bookingRepository.findByUserId(idUser);
    }
}
