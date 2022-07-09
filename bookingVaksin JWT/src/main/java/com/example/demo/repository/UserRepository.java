package com.example.demo.repository;



import java.util.Optional;
import java.util.List;
import com.example.demo.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional <User> findByEmail(String email);
    
    @Query(value = "SELECT * FROM user WHERE user.id_user = ? ", nativeQuery = true)
    Optional<User> searchById (Long id);
    @Query(value = "SELECT * FROM user WHERE (user.kota LIKE %?#{escape([0])} escape ?#{escapeCharacter()} AND user.role = 'admin') ", nativeQuery = true)
    List<User> searchByCity (String kota);
    @Query(value = "SELECT * FROM user WHERE user.role   = 'admin' ", nativeQuery = true)
    List<User> findHealth();
    @Query(value = "SELECT * FROM user WHERE user.role   = 'user' ", nativeQuery = true)
    List<User> findCitizen();

}
