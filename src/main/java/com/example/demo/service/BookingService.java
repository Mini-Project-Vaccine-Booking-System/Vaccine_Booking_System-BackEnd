package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Booking;
import com.example.demo.entity.Kelompok;
import com.example.demo.entity.Schedule;
import com.example.demo.entity.Vaksin;
import com.example.demo.entity.dto.BookingDTO;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.KelompokRepository;
import com.example.demo.repository.ScheduleRepository;
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
    ScheduleRepository scheduleRepository;

    // @Autowired
    // VaksinRepository vaksinRepository;

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
            Schedule schedule = scheduleRepository.findById(request.getIdSchedule())
                .orElseThrow(()->  new Exception( " Id Schedule" + request.getIdSchedule()+ "Not Found"));
            // Vaksin vaksin = vaksinRepository.findById(request.getIdVaksin())
            //     .orElseThrow(()->  new Exception( " Id Vaksin" + request.getIdVaksin() + "Not Found"));
    
            log.info("save booking");
            booking.setKelompok(kelompok);
            booking.setSchedule(schedule);
            // booking.setVaksin(vaksin);
            bookingRepository.save(booking);
    
            return booking;
        }
            catch(Exception e){
                log.error("save error");
                return e;
            }
    }

    public Optional<Booking> updateBooking( Long id, BookingDTO request) {
        try{    
            Optional <Booking> booking = bookingRepository.findById(id);
            Kelompok kelompok = kelompokRepository.findById(request.getIdKelompok())
                .orElseThrow(()->  new Exception( " Id User" + request.getIdKelompok() + "Not Found"));
            Schedule schedule = scheduleRepository.findById(request.getIdSchedule())
                .orElseThrow(()->  new Exception( " Id Schedule" + request.getIdSchedule()+ "Not Found"));
            // Vaksin vaksin = vaksinRepository.findById(request.getIdVaksin())
            //     .orElseThrow(()->  new Exception( " Id Vaksin" + request.getIdVaksin() + "Not Found"));

        booking.ifPresent(res -> {
            res.setKelompok(kelompok);
            res.setSchedule(schedule);
            // res.setVaksin(vaksin);
            bookingRepository.save(res);
        });
        return booking;
    }
        catch(Exception e){
            return e;
        }
    }

    public String deleteBooking(Long id) {
        Optional<Booking> bookingById = bookingRepository.findById(id);
        bookingById.ifPresent(res -> {
            bookingRepository.delete(res);
        });
        return "Deleted";
    }
}
