package com.example.demo.repository;
import com.example.demo.entity.Citizen;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen,Long> {
    // @Query(value = "SELECT * FROM citizen WHERE "+"p.nama_user = LIKE CONCAT ('%',:query,'%')")
    // List<Citizen> searchCitizen(String query);
    @Query(value = "SELECT * FROM citizen WHERE "+"p.nama_user = LIKE CONCAT ('%',:query,'%')", nativeQuery = true)
    List<Citizen> searchCitizen(String query);

}
