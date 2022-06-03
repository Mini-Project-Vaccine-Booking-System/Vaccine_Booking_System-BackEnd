package com.example.demo.entity;

import java.io.File;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "M_Car")
public class HealthFacility implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="healthID")
    private Long healthId;

    @Column(name = "name", nullable = false)
    private String name; 

    @Column(name = "petugas", nullable = false)
    private String petugas;
    
    @Column(name = "vaksin_quantity", nullable = false)
    private int vaksinQuantity;
    
    @Column(name = "jenis_vaksin", nullable = false)
    private String jenisVaksin;

    @Column(name = "dosis", nullable = false)
    private String dosis;

    @Column(name = "gambar", nullable = false)
    private File gambar;

    @Column(name = "address", nullable = false)
    private String addres;

    @Column(name = "provinsi", nullable = false)
    private String provinsi;

    @Column(name = "kota", nullable = false)
    private String kota;

    @Column(name = "pasien", nullable = false)
    private String pasien;
}
