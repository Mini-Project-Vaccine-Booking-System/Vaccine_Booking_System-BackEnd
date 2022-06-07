package com.example.demo.payload.repository;

import com.example.demo.entity.StokVaksin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StokVaksinRepository extends JpaRepository<StokVaksin, Long> {

}
