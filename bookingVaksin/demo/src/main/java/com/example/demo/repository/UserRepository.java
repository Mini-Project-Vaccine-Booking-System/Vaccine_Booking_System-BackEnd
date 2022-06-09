package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    // @Query(value = "SELECT * FROM citizen WHERE "+"p.nama_user = LIKE CONCAT ('%',:query,'%')")
    // List<User> searchCitizen(String query);
    // @Query(value = "SELECT * FROM citizen WHERE "+"p.nama_user = LIKE CONCAT ('%',:query,'%')", nativeQuery = true)
    // List<User> searchCitizenSql(String query);

}
