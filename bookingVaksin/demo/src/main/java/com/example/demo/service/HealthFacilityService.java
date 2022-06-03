package com.example.demo.service;

import com.example.demo.repository.HealthFacilityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HealthFacilityService {
    
    @Autowired 
    private HealthFacilityService(HealthFacilityRepository healthFacilityRepository){
        // this.healthFacilityRepository = healthFacilityRepository;
    }

}
 