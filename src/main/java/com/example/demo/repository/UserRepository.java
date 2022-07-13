package com.example.demo.repository;



import java.util.Optional;
import java.util.List;
import com.example.demo.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "SELECT * FROM user inner join user_roles on user.id_user=user_roles.user_id where user_roles.role_id=4 AND user.id_user = ?", nativeQuery = true)
    Optional<User> searchHealthById (Long id);
    @Query(value = "SELECT * FROM user inner join user_roles on user.id_user=user_roles.user_id where (user_roles.role_id=4 AND user.kota LIKE %?#{escape([0])} escape ?#{escapeCharacter()}) ", nativeQuery = true)
    List<User> searchByCity (String kota);
    @Query(value = "SELECT * FROM user inner join user_roles on user.id_user=user_roles.user_id where user_roles.role_id=4", nativeQuery = true)
    List<User> findHealth();
    @Query(value = "SELECT * FROM user inner join user_roles on user.id_user=user_roles.user_id where user_roles.role_id=14 ", nativeQuery = true)
    List<User> findCitizen();

    Optional<User> findByUsername(String username);
    @Query(value = "SELECT * FROM user u WHERE username = ?", nativeQuery = true)
    User findUsername(String username);

}
