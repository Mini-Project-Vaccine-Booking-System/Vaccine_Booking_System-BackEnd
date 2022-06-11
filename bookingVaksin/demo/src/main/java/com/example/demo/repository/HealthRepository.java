package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Health;

@Repository
public interface HealthRepository extends JpaRepository<Health,Long>  {
    List<Health> findByName(String name);
    // List<Health> findByNameAndAge(String name, int age);
}
