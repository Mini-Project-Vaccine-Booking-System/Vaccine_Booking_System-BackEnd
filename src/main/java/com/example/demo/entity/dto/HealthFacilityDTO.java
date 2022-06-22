package com.example.demo.entity.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class HealthFacilityDTO {
    private Long idHealth;
    private String email;
    private String password;
    private String name;
    private String address;
    private String city;
    private String image;
    private Date updated_at;
    private Date created_at;
}
