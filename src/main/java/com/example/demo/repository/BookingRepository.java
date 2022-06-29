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

    @Query(value= "select b from Booking b join b.kelompok k join k.user u where u.idUser = ?", nativeQuery=true)
    List<Booking> findByUserId(Long idUser);
}
