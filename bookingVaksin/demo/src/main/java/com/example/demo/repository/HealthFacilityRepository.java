package com.example.demo.repository;

import com.example.demo.entity.HealthFacility;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthFacilityRepository extends JpaRepository<HealthFacility,Long> {

}
