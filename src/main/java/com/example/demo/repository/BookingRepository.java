package com.example.demo.repository;

import java.util.Optional;
import java.util.List;


import com.example.demo.entity.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long>{
    @Query(value = "SELECT * FROM booking WHERE booking.id_booking = ? ", nativeQuery = true)
    Optional<Booking> searchById (Long id);

    Optional<Booking> findByKelompok_IdKelompok(Long id);
    List<Booking> findByKelompok_User_IdUser(Long idUser);
    List<Booking> findBySession_User_IdUser(Long idUser);
}
