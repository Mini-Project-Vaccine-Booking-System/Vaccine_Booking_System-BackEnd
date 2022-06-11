package com.example.demo.repository;



import com.example.demo.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    // @Query(value = "SELECT * FROM user WHERE "+
    // "p.nama_user = LIKE CONCAT ('%',:query,'%')")
    // List<User> searchUsers(String query);
    // @Query(value = "SELECT * FROM user WHERE "+"p.nama_user = LIKE CONCAT ('%',:query,'%')", nativeQuery = true)
    // List<User> searchUsersSQL(String query);

}
