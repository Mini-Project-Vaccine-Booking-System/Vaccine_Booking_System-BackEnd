package com.example.demo.entity.dto;

import java.io.File;
import java.io.Serializable;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthFacilityRequest implements Serializable {
   
    private Long healthId;
    private String name; 
    private String petugas;
    private int vaksinQuantity;
    private String jenisVaksin;
    private String dosis;
    private File gambar;
    private String addres;
    private String provinsi;
    private String kota;
    private String pasien;
}