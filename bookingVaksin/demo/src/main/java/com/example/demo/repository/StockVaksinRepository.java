package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.StokVaksin;

@Repository
public interface StockVaksinRepository extends JpaRepository<StokVaksin,Long>  {
    
}

