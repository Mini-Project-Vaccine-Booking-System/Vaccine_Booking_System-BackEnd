package com.example.demo.repository;



import java.util.Optional;
import java.util.List;
import com.example.demo.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "SELECT * FROM user WHERE user.id_user = ? ", nativeQuery = true)
    Optional<User> searchById (Long id);
    @Query(value = "SELECT * FROM user WHERE (user.kota LIKE %?#{escape([0])} escape ?#{escapeCharacter()} AND user.roles = 'ROLE_ADMIN') ", nativeQuery = true)
    List<User> searchByCity (String kota);
    @Query(value = "SELECT * FROM user WHERE user.roles   = 'ROLE_ADMIN' ", nativeQuery = true)
    List<User> findHealth();
    @Query(value = "SELECT * FROM user WHERE user.roles   = 'ROLE_USER' ", nativeQuery = true)
    List<User> findCitizen();

    Optional<User> findByUsername(String username);
    @Query(value = "SELECT * FROM user u WHERE username = ?", nativeQuery = true)
    User findUsername(String username);

}
