package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Vaksin;

@Repository
public interface VaksinRepository extends JpaRepository<Vaksin,Long>  {
    @Query(value = "SELECT * FROM vaksin WHERE nama LIKE %?#{escape([0])} escape ?#{escapeCharacter()}", nativeQuery = true)
    Optional<Vaksin> searchByName(String nama );
    @Query(value = "SELECT id_vaksin,nama,quantity FROM vaksin v WHERE v.nama = :str AND v.id_health = :str2", nativeQuery = true)
    Optional<Vaksin> searchForSession(@Param("str") String str,@Param("str2") Long str2);
    @Query(value = "SELECT * FROM vaksin WHERE vaksin.id_vaksin = ? ", nativeQuery = true)
    Optional<Vaksin> searchById (Long id);

    List<Vaksin> findByUser_idUser(Long idUser);

}