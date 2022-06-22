package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.example.demo.entity.Kelompok;

@Repository
public interface KelompokRepository extends JpaRepository<Kelompok,Long>  {
    @Query(value = "SELECT * FROM kelompok WHERE kelompok.id_kelompok = ? ", nativeQuery = true)
    Optional<Kelompok> searchById (Long id);
}