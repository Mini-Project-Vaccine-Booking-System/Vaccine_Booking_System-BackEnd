package com.example.demo.repository;

import com.example.demo.entity.Session;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.*;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long>  {
    @Query(value = "SELECT * FROM session WHERE session.id_session = ? ", nativeQuery = true)
    Optional<Session> searchById (Long id);
    @Query(value = "SELECT stok FROM session WHERE session.id_session = ? ", nativeQuery = true)
    public int sessionStok (Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE session SET session.stok = session.stok-1 WHERE session.id_session = ? ", nativeQuery = true)
    public void setStokMinus (Long id);  
    
    List<Session> findByUser_idUser(Long idUser);
    List <Session> findByDate (Date date);
    List <Session> findByDateAndUser_kota(Date date, String kota);
}
