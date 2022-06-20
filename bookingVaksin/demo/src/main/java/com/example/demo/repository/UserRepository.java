package com.example.demo.repository;



import java.util.Optional;

import com.example.demo.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "SELECT * FROM user WHERE user.id_user = ? ", nativeQuery = true)
    Optional<User> searchById (Long id);
    // @Query(value = "SELECT * FROM user WHERE WHERE kota LIKE %?#{escape([0])} escape ?#{escapeCharacter()} ", nativeQuery = true)
    // Optional<User> searchByCity (String kota);
}
