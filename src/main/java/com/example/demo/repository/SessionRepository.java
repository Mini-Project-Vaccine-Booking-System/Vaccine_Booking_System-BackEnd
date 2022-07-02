package com.example.demo.repository;

import com.example.demo.entity.Session;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long>  {
    @Query(value = "SELECT * FROM session WHERE session.id_session = ? ", nativeQuery = true)
    Optional<Session> searchById (Long id);
    List<Session> findByUser_idUser(Long idUser);
    @Query(value="SELECT nama_vaksin, start,date, end,id_Session nama_kelompok,nik FROM session WHERE kelompok.id_kelompok = ?" ,nativeQuery=true)
    Optional<Session> forBooking (Long id);

    List <Session> findByDate (Date date);
    List <Session> findByDateAndUser_kota(Date date, String kota);
}
